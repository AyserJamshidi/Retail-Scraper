package com.ayserjamshidi.retailscrape.searchresults;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class WebSearchItem implements Runnable {


	ChromeDriver driver;
//	HtmlUnitDriver driver;
	String imageSrc, rating, listingName, promotion, priceBeforeDiscount, currentPrice, shippingPrice;

	public String threadTitle, pageUrl;
	WebElement mainElement;

	public WebSearchItem(String threadTitle, String pageUrl) {
		if (pageUrl.length() <= 0 || threadTitle.length() <= 0)
			return;

		this.threadTitle = threadTitle;
		this.pageUrl = pageUrl;

		System.setProperty("webdriver.chrome.driver", "src/main/drivers/chromedriver.exe");

		driver = new ChromeDriver();
//		driver = new HtmlUnitDriver();

		driver.get(this.pageUrl);
		driver.manage().window().setSize(new Dimension(300, 300));
	}

	@Override
	public void run() {
		try {
			throw new Exception("The thread " + threadTitle + " has no loop override.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public WebElement getFirstElement(WebElement element, By searchType) {
		List<WebElement> foundElements = element.findElements(searchType);
		return foundElements.size() > 0 ? foundElements.get(0) : null;
	}
}