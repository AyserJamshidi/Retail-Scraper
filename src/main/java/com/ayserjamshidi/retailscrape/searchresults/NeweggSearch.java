package com.ayserjamshidi.retailscrape.searchresults;

import com.ayserjamshidi.retailscrape.addons.discord.DiscordChannel;
import com.ayserjamshidi.retailscrape.addons.discord.announcing.DiscordSenderTemplate;
import com.ayserjamshidi.retailscrape.searchresults.template.NeweggSearchItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Locale;

public class NeweggSearch extends WebSearchItem {

	// Hard static vars

	final int GENERAL_WAIT_TIME_MINUTES = 10;

	public NeweggSearch(String threadName, DiscordChannel discordChannel, final int MIN_WAIT_SECONDS, final int MAX_WAIT_SECONDS, String[] pageUrl) {
		super(threadName, MIN_WAIT_SECONDS, MAX_WAIT_SECONDS, pageUrl);

		this.discordChannel = discordChannel;
		this.goodLoadVar = "";
	}

	/*public void asdf() {
		if (driver == null)
			return;

		NeweggSearchItem currentItem;
		HashMap<String, Long> hMap = new HashMap<>();
		long lastComplaint = 0;

		//noinspection InfiniteLoopStatement
		while (true) {
			int sleepInterval = (new Random()).nextInt(MAX_WAITTIME_SECONDS - MIN_WAITTIME_SECONDS) + MIN_WAITTIME_SECONDS;

			boolean shouldRetry = true;
			int retryAttempts = 0;


			while (shouldRetry) {
				try {
					retryAttempts++;
					driver.get(pageUrl);
					shouldRetry = false;
				} catch (Exception ex) {
					if (retryAttempts > 10) {
						new DiscordSenderTemplate().error("Thread " + threadTitle + " had an error while attempting to load an assigned URL.");
						shouldRetry = false;
					}
				}
			}

			System.out.println(this.getName() + " - Sleeping for " + sleepInterval + " milliseconds.");
			RetailScrape.increaseMe++;
			sleep(sleepInterval);

			if (!driver.getPageSource().toLowerCase().contains("shipped by newegg") && (System.currentTimeMillis() - lastComplaint) >= (ERROR_MINUTES * 60000)) {
				lastComplaint = System.currentTimeMillis();
				new DiscordSenderTemplate().error("Run #" + RetailScrape.increaseMe + " - " + threadTitle + " - We landed on a page that wasn't the one we intended to go!" +
						"\\nLanded on - title: " + driver.getTitle() + ", URL: " + driver.getCurrentUrl());
			}
		}
	}*/

	/*@Override
	protected boolean itemHandler(WebElement element) {
		NeweggSearchItem currentItem;
//		HashMap<String, Long> hMap = new HashMap<>();

		currentItem = new NeweggSearchItem(element); // Create a Newegg Search Item object out of this cell

		if (currentItem.isAvailable()) {
//			if (hMap.containsKey(currentItem.itemName))
//				if (System.currentTimeMillis() - hMap.get(currentItem.itemName) < (GENERAL_WAIT_TIME_MINUTES * 60000))
//					return false;

			System.out.println("[IN STOCK] " + currentItem.itemName);
			new DiscordSenderTemplate().announce(discordChannel, currentItem);

//			hMap.put(currentItem.itemName, System.currentTimeMillis());
			return true;
		}
		System.out.println("[OOS] " + currentItem.itemName);
		return false;
	}*/

	@Override
	protected boolean itemIsCombo(Object obj) {
		return ((NeweggSearchItem) obj).productUrl.contains("ComboDealDetails");
	}

	@Override
	protected boolean itemIsGood(WebElement cell) {
		return getFirstElement(cell, By.className("txt-ads-box")) == null && getFirstElement(cell, By.className("item-sponsored")) == null;
	}

	@Override
	protected boolean pageReloaded() {
		if (!driver.getPageSource().toLowerCase().contains("shipped by newegg")) { // If we didn't load the page properly
//			if ((System.currentTimeMillis() - lastComplaint) >= (ERROR_WAIT_MINUTES * 60000)) // If we recently sent a complaint, stop here
//				return false;
//
//			lastComplaint = System.currentTimeMillis();
//			new DiscordSenderTemplate().error("[" + this.getName() + "] - We landed on a page that wasn't the one we intended to go!" +
//					"(" + driver.getTitle() + ") : " + driver.getCurrentUrl());
			return false;
		}

		return true;
	}

	@Override
	protected void badPageReload() {
		String title = driver.getTitle().toLowerCase();

		if (title.contains("are you a human?")) { // IP Flag
			new DiscordSenderTemplate().error("[" + this.getName() + "] - IP has been flagged.  Change VPN source!");
			System.out.println("IP has been flagged.");
		} else if (title.contains("403 error")) {
			System.out.println("IP has been banned.");
			new DiscordSenderTemplate().error("[" + this.getName() + "] - IP has been banned.  Change VPN source!");
		} else {
			new DiscordSenderTemplate().error("[" + this.getName() + "] - Something happened while trying to reload URL.");
		}
	}
}