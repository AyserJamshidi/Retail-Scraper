package com.ayserjamshidi.retailscrape.searchresults;

import com.ayserjamshidi.retailscrape.ThreadList.AmazonTrackingList;
import com.ayserjamshidi.retailscrape.addons.discord.DiscordChannel;
import com.ayserjamshidi.retailscrape.threads.DiscordAnnouncer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class AmazonSearch extends WebSearch {

	ChromeOptions options = new ChromeOptions();

	final double maxPriceHikePercent = 0.10;
	double maxPrice;

	public AmazonSearch(final String threadName, final DiscordChannel discordChannel, final AmazonTrackingList amazonTrackingList) {
		super(threadName, discordChannel, 10, 15, new String[]{amazonTrackingList.itemUrl}, By.cssSelector(".a-row.a-spacing-mini.olpOffer"));
		amazonItemDetails = amazonTrackingList;
		maxPrice = amazonItemDetails.itemMSRP * (1.0 + maxPriceHikePercent);
	}

	@Override
	public void run() {
		System.setProperty("webdriver.chrome.driver", "src/main/drivers/chromedriver" + (System.getProperty("os.name").contains("Mac OS") ? "" : ".exe"));
//		options.setCapability("proxy", proxy);

		options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

		// Disable JS
		Map<String, Object> chromePrefs = new HashMap<>();
		chromePrefs.put("profile.managed_default_content_settings.javascript", 2);
		chromePrefs.put("profile.managed_default_content_settings.images", 2);
		options.setExperimentalOption("prefs", chromePrefs);

		driver = new ChromeDriver(options);

		reinitializeDriver();

		super.run();
	}

	@Override
	public boolean itemIsValid(final WebElement element) {
		final WebElement itemCondition = getFirstElement(element, By.className("olpCondition"));
		final String itemPrice = getFirstElementText(element, By.className("olpOfferPrice"));

		if (itemPrice != null) {
			final double itemPriceActual = Float.parseFloat(itemPrice.replace("$", "").replace(",", ""));

			if (itemPriceActual >= amazonItemDetails.itemMSRP * 0.85 && itemPriceActual <= maxPrice)
				return itemCondition != null && itemCondition.getText().equals("New");
		} else {
			DiscordAnnouncer.queueError("We got a null price when looking for an Amazon item.");
		}
		return false;
	}

	@Override
	protected boolean pageReloaded() {
		return driver.getTitle().contains("Buying Choices");
	}

	@Override
	protected void badPageReload() {
		reinitializeDriver();
	}

	private void reinitializeDriver() {
		System.out.println("Attempting to land on the offer-listing page for " + amazonItemDetails.itemName);
		driver.manage().deleteAllCookies();
	}
}