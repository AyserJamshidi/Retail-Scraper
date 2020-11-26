package com.ayserjamshidi.retailscrape.website;

import com.ayserjamshidi.retailscrape.DiscordChannel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.net.URI;

public class Newegg extends WebItem {

	boolean isCombo;
	By addToCartBy, imageSrcBy, itemTitleBy;

	public Newegg(String threadTitle, String pageUrl) {
		super(); // Initialize WebDriver
		this.threadTitle = threadTitle;
		this.pageUrl = pageUrl;

		driver.get(this.pageUrl);

		isCombo = this.pageUrl.toLowerCase().contains("combodeal");
		imageSrcBy = isCombo ? By.id("mainSlide_0") : By.className("product-view-img-original");
		itemTitleBy = By.className(isCombo ? "wrapper" : "product-title");
		addToCartBy = By.className(isCombo ? "atnPrimary" : "product-buy");
	}

	@Override
	public void run() {
		System.out.println("Initializing " + this.threadTitle + "...");

//		System.setProperty("webdriver.edge.driver", "src/main/drivers/msedgedriver.exe");
		//driver = new EdgeDriver();

		while (true) {
			try {
				WebElement thing = getFirstElement(addToCartBy);

				if (thing != null)
					if (thing.getText().toLowerCase().contains("add to cart")) { // In stock!
						System.out.println("Found!");

						discordAnnouncement(DiscordChannel.MAIN_CHANNEL);

						Desktop.getDesktop().browse(new URI(pageUrl.replaceAll(" ", "%20")));
						sleep(60000 * 5);
//					thing.findElement(By.className("btn-primary")).click();
					} else {
						System.out.println(this.threadTitle + " is OOS.  We found \"" + thing.getText() + "\"");
					}
			} catch (Exception e) {
				System.out.println("error occurred for item " + this.threadTitle + ":");
				e.printStackTrace();
				System.exit(-1);
			}

			driver.navigate().refresh();
//			driver.get(this.url); // Re re-get the URL instead of refreshing because sometimes newegg will redirect us to bot-verification.

			sleep(3000);
		}

//		driver.close();
	}

	@Override
	public void discordAnnouncement(DiscordChannel channel) {
		WebElement imgSrcElement = getFirstElement(imageSrcBy);
		WebElement titleElement = getFirstElement(itemTitleBy);

		if (imgSrcElement != null)
			imageSrc = imgSrcElement.getAttribute("src");

		if (titleElement != null)
			threadTitle = titleElement.getText();

		price = driver.findElement(By.className(isCombo ? "current" : "price-current")).getText().replace("Now: ", "");
//		shippingCost = this.getFirstelement(By.className(""));

		// Try/catch this because not every item/combo has promotions and Selenium has no checks before finding elements.
		WebElement testy = this.getFirstElement(By.className(isCombo ? "promo" : "product-promo"));
		promotion = (testy != null) ? testy.getText() : null;

		super.discordAnnouncement(channel);
	}
}
