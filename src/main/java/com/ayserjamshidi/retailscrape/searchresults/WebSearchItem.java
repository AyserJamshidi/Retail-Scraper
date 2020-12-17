package com.ayserjamshidi.retailscrape.searchresults;

import com.ayserjamshidi.retailscrape.addons.discord.DiscordChannel;
import com.ayserjamshidi.retailscrape.addons.discord.announcing.DiscordSenderTemplate;
import com.ayserjamshidi.retailscrape.searchresults.template.NeweggSearchItem;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v87.network.Network;

import java.util.*;

public class WebSearchItem extends Thread {
	// Static variables
	final int REFRESH_ATTEMPTS = 10; // Page refresh attempts
	final int ERROR_WAIT_MINUTES = 4; // Minutes before reporting another error for the same thread
	final int GENERAL_WAIT_MINUTES = 10; // Minutes before announcing an item that was already announced
	final int MIN_WAIT_SECONDS, MAX_WAIT_SECONDS; // Set on init

	int amountOfCombosAnnounced = 0;
	long comboTimeLimit = 0;

	HashMap<String, Long> announcedItems = new HashMap<>(); // Contains a list and time of announced items

	String goodLoadVar = null;

	//	HtmlUnitDriver driver;
	ChromeDriver driver = null;
	DiscordChannel discordChannel = null;

	String[] pageUrls = null;
	By byThing = By.className("item-cell"); // TODO: Make this a changeable variable

	private long lastComplaint = 0;

	public WebSearchItem(String threadName, final int MIN_WAIT_SECONDS, final int MAX_WAIT_SECONDS, String[] pageUrls) {
		if (pageUrls.length <= 0 || threadName.length() <= 0)
			throw new InvalidArgumentException("threadName and pageURL must have at least 1 character when creating a thread!");

		// Thread setup
		this.setName(threadName);

		// Class setup
		System.out.println("Initializing " + this.getName() + "...");
		this.pageUrls = pageUrls;
		this.MIN_WAIT_SECONDS = milliToSeconds(MIN_WAIT_SECONDS);
		this.MAX_WAIT_SECONDS = milliToSeconds(MAX_WAIT_SECONDS);

		// Driver setup
		System.setProperty("webdriver.chrome.driver", "src/main/drivers/chromedriver.exe");
		this.driver = new ChromeDriver();
		this.driver.manage().window().setSize(new Dimension(300, 300));
	}

	@Override
	public void run() {
		long lastZotacTime = 0;
		String lastComboAnnounced = "";

		while (!this.isInterrupted()) { // While this thread isn't asked to end
			for (String currentUrl : pageUrls) {
				driver.get(currentUrl);
				int sleepTime = randomMilliToSeconds(5, 10); // TODO: Set these two numbers as variables later

				System.out.println(this.getName() + " - Sleeping for " + sleepTime / 1000 + " seconds.");
				sleep(sleepTime);

				try {
					if (pageReloaded()) {
						for (WebElement curPossibleItem : driver.findElements(byThing)) { // Check for any possible items
							if (itemIsGood(curPossibleItem)) { // Ensure current element passes all checks to be qualified a good item
								NeweggSearchItem currentItem = new NeweggSearchItem(curPossibleItem);


								if (currentItem.isAvailable() && canAnnounce(curPossibleItem)) { // Returns true if current item is in stock, false otherwise
									// TODO: Change NeweggSearchItem when implementing perfect scaling

									// TODO: REMOVE THIS AFTER WE ADD JDA BOT RECOGNITION
									if (currentItem.productUrl.toLowerCase().contains("N82E16814500503".toLowerCase())) {
										if (System.currentTimeMillis() - lastZotacTime < 30 * 60000)
											continue;

										lastZotacTime = System.currentTimeMillis();
									}

									if (itemIsCombo(currentItem)) { // If combo
										if ((System.currentTimeMillis() - comboTimeLimit) < 15 * 60000) // If we've hit the limit previously
											continue; // We've recently hit the combo limit, shush up for a bit.

										if (amountOfCombosAnnounced >= 2) { // If we already didn't announce a lot
											comboTimeLimit = System.currentTimeMillis(); // Set combo limit time

											new Thread() {
												public void run() {
													try {
														sleep(milliToMinutes(30)); // Sleep for 30 minutes
														amountOfCombosAnnounced = 0; // Reset comboCounter so we can shout some again
													} catch (InterruptedException e) {
														e.printStackTrace();
													}
												}
											};
											continue;
										}

										// We can call it!
										amountOfCombosAnnounced++;
										lastComboAnnounced = currentItem.itemName;
									}

									announcedItems.put(currentItem.itemName, System.currentTimeMillis());

									new DiscordSenderTemplate().announce(discordChannel, currentItem, itemIsCombo(currentItem));
									sleep(5000); // Sleep to avoid denied webhook requests and too much ping spam
								}
							}
						}
					} else if (canComplain()) {
						badPageReload();
					}
				} catch (Exception ex) {
					new DiscordSenderTemplate().error("Error occurred while doing standard loop for thread " + this.getName());
					System.out.println(Arrays.toString(ex.getStackTrace()));
				}

//			hardRefresh(); // Delete cookies/cache

				pageReload(currentUrl);

//			new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(driver.findElement(By.className(""))));
			}
		}
	}

	protected boolean itemIsCombo(Object obj) {
		/// Override expected if combo deals are possible
		return false;
	}

//	protected boolean itemHandler(WebElement element) throws Exception { // We get here when itemIsGood is true
//		/// Override expected
//		throw new Exception("[" + this.getName() + "] - itemHandler is not overridden.");
//	}

	protected boolean itemIsGood(WebElement element) throws Exception { // Used inside of a loop
		/// Override expected
		throw new Exception("[" + this.getName() + "] - itemIsGood is not overridden.");
	}

	protected boolean pageReloaded() throws Exception {
		/// Override expected
		throw new Exception("[" + this.getName() + "] - pageReloaded is not overridden.");
	}

	protected void badPageReload() throws Exception {
		/// Override expected
		throw new Exception("[" + this.getName() + "] - badPageReload is not overridden.");
	}

	protected WebElement getFirstElement(WebElement element, By searchType) {
		try {
			return element.findElement(searchType);
		} catch (Exception ex) {
			return null;
		}

//		List<WebElement> foundElements = element.findElements(searchType);
//		return foundElements.size() > 0 ? foundElements.get(0) : null;
	}

	private void pageReload(String currentUrl) {
		for (int i = 0; i < REFRESH_ATTEMPTS; i++) {
			try {
				driver.get(currentUrl);
				break;
			} catch (Exception ex) {
				if (i == REFRESH_ATTEMPTS - 1)
					new DiscordSenderTemplate().error("[" + this.getName() + "] - Error while attempting to load assigned URL.");
				sleep(10000);
			}
		}
	}

	private boolean canAnnounce(WebElement element) {
		NeweggSearchItem currentItem = new NeweggSearchItem(element);

		if (announcedItems.containsKey(currentItem.itemName))
			return (System.currentTimeMillis() - announcedItems.get(currentItem.itemName) > (GENERAL_WAIT_MINUTES * 60000));

		return true;
	}

	private boolean canComplain() {
		if (System.currentTimeMillis() - lastComplaint > (ERROR_WAIT_MINUTES * 60000)) {
			lastComplaint = System.currentTimeMillis();
			return true;
		}

		return false;
	}

	private void hardRefresh() {
		driver.manage().deleteAllCookies();
		driver.getDevTools().createSessionIfThereIsNotOne();
		driver.getDevTools().send(Network.clearBrowserCache());
	}

	protected int milliToMinutes(int i) {
		return i * 60000;
	}

	protected int milliToSeconds(int i) {
		return i * 1000;
	}

	protected int randomMilliToSeconds(int minSeconds, int maxSeconds) {
		return (new Random().nextInt(maxSeconds - minSeconds) + minSeconds) * 1000;
	}

	protected void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}