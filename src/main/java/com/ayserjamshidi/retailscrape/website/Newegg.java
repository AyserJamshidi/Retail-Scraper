package com.ayserjamshidi.retailscrape.website;

import com.ayserjamshidi.retailscrape.searchitem.NeweggSearchResultItemTemplate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.awt.*;
import java.net.URI;
import java.util.List;

public class Newegg extends WebsiteTemplate implements Runnable {

	@Override
	public void run() {

	}

	/*public void run(String searchTitle, final String searchUrl) {
		System.out.println("Initializing " + searchTitle + "...");
		HtmlUnitDriver driver = new HtmlUnitDriver(false);

		while (true) {
			driver.get(searchUrl);
//			driver.get("https://www.newegg.com/p/pl?N=100007709%20601357282&PageSize=96");

			List<WebElement> test = driver.findElementsByClassName("item-container");

			for (WebElement currentItem : test) {
				NeweggSearchResultItemTemplate testyItem = new NeweggSearchResultItemTemplate(currentItem);

				if (!testyItem.promotion.equals("OUT OF STOCK")) {
					try {
						Desktop.getDesktop().browse(new URI(testyItem.link.replaceAll(" ", "%20")));
						System.out.println("Found " + testyItem.listingName);
						System.out.println("Stopping, rerun me if you want to find more items.");
						System.exit(0);
					} catch (Exception e) {
						System.out.println("Error occurred while trying to open " + testyItem.listingName + "\n\n" + e);
					}
				} else {
					System.out.println("[" +searchTitle + "] " + testyItem.listingName + " - OOS, skipping...");
				}
			}

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}*/

	@Override
	public boolean outOfStock(WebElement currentItem) {
		WebElement itemStock = currentItem.findElement(By.className("item-promo"));

		return itemStock.getText().toLowerCase().contains("out of stock");
	}
}
