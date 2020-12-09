package com.ayserjamshidi.retailscrape.searchresults;

import com.ayserjamshidi.retailscrape.RetailScrape;
import com.ayserjamshidi.retailscrape.addons.discord.DiscordChannel;
import com.ayserjamshidi.retailscrape.addons.discord.announcing.DiscordSenderTemplate;
import com.ayserjamshidi.retailscrape.searchresults.template.NeweggSearchItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Random;

public class NeweggSearch extends WebSearchItem {

	final int GENERAL_WAIT_TIME_MINUTES = 10;
	final int MIN_WAITTIME_SECONDS, MAX_WAITTIME_SECONDS;
	DiscordChannel discordChannel;

	public NeweggSearch(String threadTitle, DiscordChannel discordChannel, int minWaitTimeSecs, int maxWaitTimeSecs, String searchPageUrl) {
		super(threadTitle, searchPageUrl);

		this.MIN_WAITTIME_SECONDS = minWaitTimeSecs * 1000;
		this.MAX_WAITTIME_SECONDS = maxWaitTimeSecs * 1000;
		this.discordChannel = discordChannel;
	}

	@Override
	public void run() {
		if (driver == null)
			return;

		System.out.println("Initializing " + this.getClass().getSimpleName() + " thread \"" + threadTitle + "\"...");
		NeweggSearchItem currentItem;
		HashMap<String, Long> hMap = new HashMap<>();
		long lastComplaint = 0;

		//noinspection InfiniteLoopStatement
		while (true) {
			int sleepInterval = (new Random()).nextInt(MAX_WAITTIME_SECONDS - MIN_WAITTIME_SECONDS) + MIN_WAITTIME_SECONDS;

			try {
				for (WebElement currentCell : driver.findElements(By.className("item-cell"))) { // Loop through every found item
					if (cellIsNotAd(currentCell)) { // If item is NOT an ad
						currentItem = new NeweggSearchItem(currentCell); // Create a Newegg Search Item object out of this cell

						if (currentItem.isAvailable()) {
							if (hMap.containsKey(currentItem.itemName))
								if (System.currentTimeMillis() - hMap.get(currentItem.itemName) < (GENERAL_WAIT_TIME_MINUTES * 60000))
									continue;

							System.out.println("Found!!!! " + currentItem.itemName);
							new DiscordSenderTemplate().announce(discordChannel, currentItem);

							hMap.put(currentItem.itemName, System.currentTimeMillis());
						}
					} else {
						System.out.println("Skipping Ad...");
					}
				}
			} catch (Exception e) {
				new DiscordSenderTemplate().error("Error occurred while doing standard loop for thread " + threadTitle);
			}

			System.out.println(threadTitle + " - Sleeping for " + sleepInterval + " milliseconds.");

			boolean shouldRetry = true;
			int retryAttempts = 0;

			while (shouldRetry) {
				try {
					retryAttempts++;
					driver.manage().deleteAllCookies();
					driver.get(pageUrl);
					shouldRetry = false;
				} catch (Exception ex) {
					if (retryAttempts > 10) {
						new DiscordSenderTemplate().error("Thread " + threadTitle + " had an error while attempting to load an assigned URL.");
						shouldRetry = false;
					}
				}
			}

			RetailScrape.increaseMe++;
			sleep(sleepInterval);

			if (!driver.getPageSource().toLowerCase().contains("today's best deal") && (System.currentTimeMillis() - lastComplaint) >= (2 * 60000)) {
				lastComplaint = System.currentTimeMillis();
				new DiscordSenderTemplate().error("Run #" + RetailScrape.increaseMe + " - " + threadTitle + " - We landed on a page that wasn't the one we intended to go!\\nLanded on: " + driver.getCurrentUrl());
			}
		}
	}

	private boolean cellIsNotAd(WebElement cell) {
		return getFirstElement(cell, By.className("txt-ads-box")) == null && getFirstElement(cell, By.className("item-sponsored")) == null;
	}

	public String toString() {
		return "Image Link: " + this.imageSrc +
				"\nProduct Link: " + this.pageUrl +
				"\nRating: " + this.rating +
				"\nListing Name: " + this.listingName +
				"\nPromotion Thing: " + this.promotion +
				"\nPrice before discount: " + this.priceBeforeDiscount +
				"\nCurrent price: " + this.currentPrice +
				"\nShipping price: " + this.shippingPrice;
	}
}