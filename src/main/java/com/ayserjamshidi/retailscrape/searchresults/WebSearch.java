package com.ayserjamshidi.retailscrape.searchresults;

import java.util.Map;
import java.util.Random;

import com.ayserjamshidi.retailscrape.searchresults.searcheditem.AmazonSearchItem;
import com.ayserjamshidi.retailscrape.searchresults.searcheditem.BestBuySearchedItem;
import com.ayserjamshidi.retailscrape.searchresults.searcheditem.NeweggSearchItem;
import com.ayserjamshidi.retailscrape.searchresults.searcheditem.TemplateSearchItem;

import com.ayserjamshidi.retailscrape.addons.discord.DiscordAnnounce;
import org.openqa.selenium.WebElement;
import com.ayserjamshidi.retailscrape.addons.mudfish.Fix;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import com.ayserjamshidi.retailscrape.addons.discord.DiscordChannel;

import java.util.HashMap;

public class WebSearch extends Thread {
	final int REFRESH_ATTEMPTS = 10;
	final int ERROR_WAIT_MINUTES = 4;
	final int GENERAL_WAIT_MINUTES = 35;
	final int MIN_WAIT_SECONDS;
	final int MAX_WAIT_SECONDS;

	int amountOfCombosAnnounced;
	long comboTimeLimit;
	HashMap<String, Long> announcedItems;
	DiscordChannel discordChannel;
	By itemSearchTerm;
	private long lastComplaint;
	WebDriver driver;
	String[] pageUrls;
	String lastAnnouncedItem;

	public WebSearch(final String threadName, final DiscordChannel discordChannel, final int MIN_WAIT_SECONDS, final int MAX_WAIT_SECONDS, final String[] pageUrls, final By itemSearchTerm) {
		this.amountOfCombosAnnounced = 0;
		this.comboTimeLimit = 0;
		this.announcedItems = new HashMap<>();
		this.discordChannel = null;
		this.lastComplaint = 0;
		this.lastAnnouncedItem = "";

		if (pageUrls.length <= 0 || threadName.length() <= 0) {
			System.out.println("BAD!!!!!");
			System.exit(-1);
		}

		this.setName(threadName);
		System.out.println("Initializing " + this.getName() + "...");

		this.pageUrls = pageUrls;
		this.discordChannel = discordChannel;
		this.MIN_WAIT_SECONDS = MIN_WAIT_SECONDS;
		this.MAX_WAIT_SECONDS = MAX_WAIT_SECONDS;
		this.itemSearchTerm = itemSearchTerm;

		System.setProperty("webdriver.chrome.driver", System.getProperty("os.name").contains("Mac OS") ? "src/main/drivers/chromedriver" : "src/main/drivers/chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addExtensions(new File("src/main/extensions/Mudfish-HTTP-Proxy_v4.4.8.crx"));

		options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

//		Map<String, Object> chromePrefs = new HashMap<>();
//		chromePrefs.put("profile.managed_default_content_settings.javascript", 2);
//		options.setExperimentalOption("prefs", chromePrefs);

		this.driver = new ChromeDriver(options);
		this.driver.manage().window().setSize(new Dimension(300, 180));
	}

	@Override
	public void run() {
		if (this.pageUrls[0].toLowerCase().contains("newegg")) {
			System.out.println("[" + this.getName() + "] - Setting VPN");
			Fix.thing(this.driver);
			System.out.println("Done");
		}

		while (!this.isInterrupted()) {
			for (final String currentUrl : pageUrls) {
				driver.get(currentUrl);
				sleep(randomMilliToSeconds(MIN_WAIT_SECONDS, MAX_WAIT_SECONDS));

				System.out.println(driver.getPageSource() + "\n");
				System.exit(-1);

				try {
					if (pageReloaded()) {
						for (final WebElement curPossibleItem : driver.findElements(itemSearchTerm)) {
							if (itemIsGood(curPossibleItem)) {
								final TemplateSearchItem currentItem = testFunction(curPossibleItem, currentUrl);
								if (currentItem == null)
									throw new Exception("Something happened while trying to create a new object!");

								if (!currentItem.isAvailable() || !canAnnounce(currentItem.itemName))
									continue;

								if (itemIsCombo(currentItem)) {
									if (System.currentTimeMillis() - comboTimeLimit < 900000)
										continue;

									if (amountOfCombosAnnounced >= 2) {
										comboTimeLimit = System.currentTimeMillis();

										new Thread() {
											@Override
											public void run() {
												try {
													Thread.sleep(milliToMinutes(30));
													amountOfCombosAnnounced = 0;
												} catch (InterruptedException e) {
													e.printStackTrace();
												}
											}
										};
										continue;
									}
									amountOfCombosAnnounced++;
								}
								System.out.println("[IN STOCK] " + currentItem.itemName);
								lastAnnouncedItem = currentItem.itemName;
								announcedItems.put(currentItem.itemName, System.currentTimeMillis());
								DiscordAnnounce.announce(this.discordChannel, currentItem, this.itemIsCombo(currentItem));
								sleep(500);
							}
						}
					} else if (canComplain()) {
						System.out.println("Complaining time");
						badPageReload();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				pageReload(currentUrl);
			}
		}

		new DiscordAnnounce().error("[" + this.getName() + "] - Thread has crashed for some unknown reason. Restart the bot!");
	}

	protected boolean itemIsCombo(final Object obj) {
		return false;
	}

	protected boolean itemIsGood(final WebElement element) throws Exception {
		throw new Exception("[" + this.getName() + "] - itemIsGood is not overridden.");
	}

	protected boolean pageReloaded() {
		return true;
	}

	protected void badPageReload() throws Exception {
		throw new Exception("[" + this.getName() + "] - badPageReload is not overridden.");
	}

	protected String getFirstElementText(final WebElement element, final By searchType) {
		try {
			return element.findElement(searchType).getText();
		} catch (Exception ex) {
			return null;
		}
	}

	protected WebElement getFirstElement(final WebElement element, final By searchType) {
		try {
			return element.findElement(searchType);
		} catch (Exception ex) {
			return null;
		}
	}

	private TemplateSearchItem testFunction(final WebElement curPossibleItem, final String currentUrl) {
		switch (this.getClass().getSimpleName()) {
			case "NeweggSearch":
				return new NeweggSearchItem(curPossibleItem);
			case "BestBuySearch":
				return new BestBuySearchedItem(curPossibleItem);
			case "AmazonSearch":
				return new AmazonSearchItem(curPossibleItem);
			default: {
				System.out.println("What happened??  == " + this.getClass().getSimpleName());
				return null;
			}
		}
	}

	private void pageReload(final String currentUrl) {
		this.driver.get(currentUrl);
	}

	private boolean canAnnounce(final String itemName) {
		if (!this.announcedItems.containsKey(itemName)) {
			return true;
		}

		if (this.lastAnnouncedItem.equals(itemName)) {
			return System.currentTimeMillis() - this.announcedItems.get(itemName) > 3600000L;
		}

		return System.currentTimeMillis() - this.announcedItems.get(itemName) > 2100000L;
	}

	private boolean canComplain() {
		if (System.currentTimeMillis() - this.lastComplaint > 240000L) {
			this.lastComplaint = System.currentTimeMillis();
			return true;
		}
		return false;
	}

	protected int milliToMinutes(final int i) {
		return i * 60000;
	}

	protected int randomMilliToSeconds(final int minSeconds, final int maxSeconds) {
		return (new Random().nextInt(maxSeconds - minSeconds) + minSeconds) * 1000;
	}

	protected void sleep(final int ms) {
		try {
			System.out.println(this.getName() + " - Sleeping for " + ms + " ms.");
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}