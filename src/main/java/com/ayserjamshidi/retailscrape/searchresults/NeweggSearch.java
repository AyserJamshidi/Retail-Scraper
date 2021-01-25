package com.ayserjamshidi.retailscrape.searchresults;

import com.ayserjamshidi.retailscrape.RetailScrape;
import com.ayserjamshidi.retailscrape.addons.discord.DiscordAnnounce;
import com.ayserjamshidi.retailscrape.addons.discord.DiscordChannel;
import com.ayserjamshidi.retailscrape.searchresults.searcheditem.NeweggSearchItem;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class NeweggSearch extends WebSearch {

	public NeweggSearch(final String threadName, final DiscordChannel discordChannel, final String[] pageUrl) {
		super(threadName, discordChannel, 60, 120, pageUrl, By.className("item-cell"));
	}

	@Override
	public void run() {
		// Init
//		driver = new HtmlUnitDriverErrorless(false);

		// Setup proxy
//		proxy = new Proxy();
		setupProxy();

		// Call parent
		super.run();
	}

	@Override
	protected boolean itemIsCombo(final Object obj) {
		return ((NeweggSearchItem) obj).itemUrl.contains("ComboDealDetails");
	}

	@Override
	protected boolean itemIsValid(final WebElement cell) {
		/*String[] blacklistedItems = {
				"RTX3090-O24G-GAMING",
				"ZT-A30900D-10P",
				"RTX 3070 SUPRIM 8G",
				"UF-RTX3070-O8G-GAMING"
		};

		for (String curBlacklistItem : blacklistedItems)
			if (cell.getText().contains(curBlacklistItem))
				return false;*/

		/*String[] whitelistAvailability = {
				// 3060ti
				"DUAL-RTX3060TI-8G",
				"08G-P5-3663-KR",
				"GV-N306TEAGLE OC-8GD",
				"GV-N306TEAGLE-8GD",
				"GV-N306TGAMING OC-8GD",

				// 3080
				"VCG308010TFXPPB",
				"RTX 3080 VENTUS 3X 10G",
				"GV-N3080EAGLE-10GD",
				"GV-N3080EAGLE OC-10GD",
				"10G-P5-3881-KR",
		};

		for (String currentItemName : whitelistAvailability)
			if (cell.getText().contains(currentItemName))
				return true;

		if (true)
			return false;*/

		String loweredText = cell.getText().toLowerCase();

		return !(loweredText.contains("gaming box") || loweredText.contains("egpu")
				|| loweredText.contains("seeing this ad") || loweredText.contains("shop now"));
	}

	@Override
	protected void setupProxy() {
		if (RetailScrape.proxyList.size() > 0) {
			if (RetailScrape.proxyIndex == RetailScrape.proxyList.size()) // Reset index so we don't go OOB
				RetailScrape.proxyIndex = 0;

//			proxy.setHttpProxy(RetailScrape.proxyList.get(RetailScrape.proxyIndex));
//			proxy.setSslProxy(RetailScrape.proxyList.get(RetailScrape.proxyIndex++));

			System.setProperty("webdriver.chrome.driver", "src/main/drivers/chromedriver" + (System.getProperty("os.name").contains("Mac OS") ? "" : ".exe"));

			// Chrome shit
			ChromeOptions options = new ChromeOptions();
//			options.setCapability("proxy", proxy);

			options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

			// Disable JS
			Map<String, Object> chromePrefs = new HashMap<>();
			chromePrefs.put("profile.managed_default_content_settings.javascript", 2);
			chromePrefs.put("profile.managed_default_content_settings.images", 2);
			options.setExperimentalOption("prefs", chromePrefs);

			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(180, 180));

			//((HtmlUnitDriver) driver).setProxySettings(proxy);
//			sendMessage("Using index " + RetailScrape.proxyIndex + ", proxy " + proxy.getHttpProxy());
		} else {
			sendMessage("Can't setup proxy, we're out!");
		}
	}

	@Override
	protected boolean pageReloaded() {
		return driver.getPageSource().toLowerCase().contains("shipped by newegg");
	}

	@Override
	protected void badPageReload() {
		final String loweredTitle = driver.getTitle().toLowerCase();
		String outputMessage = null;

		if (loweredTitle.contains("are you a human?")) {
//			setupProxy();
			DiscordAnnounce.error("[" + this.getName() + "] - Human crap is back...");
		} else if (loweredTitle.contains("403 error")) {
			outputMessage = "IP banned. Change VPN source!";
		} else {
			outputMessage = "Something happened while trying to reload URL";
		}

		if (outputMessage != null)
			DiscordAnnounce.error("[" + this.getName() + "] - " + outputMessage);
	}
}