package com.ayserjamshidi.retailscrape.searchresults;

import com.ayserjamshidi.retailscrape.RetailScrape;
import com.ayserjamshidi.retailscrape.addons.discord.DiscordChannel;
import com.ayserjamshidi.retailscrape.drivers.HtmlUnitDriverErrorless;
import com.ayserjamshidi.retailscrape.searchresults.itemtemplate.TemplateSearchItem;
import com.ayserjamshidi.retailscrape.threads.DiscordAnnouncer;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebElement;

public class NeweggSearch extends WebSearch {

	String[] blacklistedItems = {
			"RX6900XT PGD 16GO",
			"ZT-A30900D-10P",
			"VCG309024TFXPPB"
	};

	public NeweggSearch(final String threadName, final DiscordChannel discordChannel, final String[] pageUrl) {
		super(threadName, discordChannel, 5, 10, pageUrl, By.className("item-cell"));
	}

	@Override
	public void run() {
		// Init
		driver = new HtmlUnitDriverErrorless(false);

		// Setup proxy
		proxy = new Proxy();
		setupProxy();

		// Call parent
		super.run();
	}

	/*@Override
	protected boolean itemIsCombo(final TemplateSearchItem searchedItem) {
		return searchedItem.itemUrl.contains("ComboDealDetails");
	}*/

	@Override
	protected boolean itemIsValid(final WebElement cell) {
		for (String curBlacklistItem : blacklistedItems)
			if (cell.getText().contains(curBlacklistItem))
				return false;

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
	protected void afterLoadTasks() {
		driver.manage().deleteAllCookies();
	}

	@Override
	protected void setupProxy() {
		if (RetailScrape.proxyList.size() > 0) {
			if (RetailScrape.proxyIndex == RetailScrape.proxyList.size()) // If OOB
				RetailScrape.proxyIndex = 0; // Start at the beginning again

			proxy.setHttpProxy(RetailScrape.proxyList.get(RetailScrape.proxyIndex));
			proxy.setSslProxy(RetailScrape.proxyList.get(RetailScrape.proxyIndex++));

			((HtmlUnitDriverErrorless) driver).setProxySettings(proxy);

			sendMessage("IP is now " + proxy.getHttpProxy());
			// Chrome shit
			/*
			if (driver != null)
				driver.close();

			System.setProperty("webdriver.chrome.driver", "src/main/drivers/chromedriver" + (System.getProperty("os.name").contains("Mac OS") ? "" : ".exe"));
			ChromeOptions options = new ChromeOptions();
			options.setCapability("proxy", proxy);

			options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

			// Disable JS
			Map<String, Object> chromePrefs = new HashMap<>();
			chromePrefs.put("profile.managed_default_content_settings.javascript", 2);
			chromePrefs.put("profile.managed_default_content_settings.images", 2);
			options.setExperimentalOption("prefs", chromePrefs);

			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(180, 180));*/
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

		if (loweredTitle.contains("are you a human")) {
//			if (System.currentTimeMillis() - lastGoodLoad >= 3 * 60000) {
//				hasntComplainedYet = true;
//				lastGoodLoad = System.currentTimeMillis();
			setupProxy();
			sendMessage("IP has changed to " + proxy.getHttpProxy());

//			DiscordAnnouncer.queueError("[" + this.getName() + "] - Bot detection triggered...  Changing IP.");
		} else if (loweredTitle.contains("403 error")) {
			outputMessage = "IP banned. Change VPN source!";
		} else {
			outputMessage = "Something happened while trying to reload URL, title is: " + driver.getTitle();
		}

		if (outputMessage != null)
			DiscordAnnouncer.queueError("[" + this.getName() + "] - " + outputMessage);
	}
}