package com.ayserjamshidi.retailscrape.searchresults;

import com.ayserjamshidi.retailscrape.addons.discord.DiscordChannel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class BestBuySearch extends WebSearch {

	public BestBuySearch(final String threadName, final DiscordChannel discordChannel, final String[] pageUrls) {
		super(threadName, discordChannel, 15, 20, pageUrls, By.className("sku-item"));
	}

	@Override
	public void run() {
//		driver = new HtmlUnitDriverErrorless(false);

		super.run();
	}

	@Override
	protected void afterLoadTasks() {
		Actions actions = new Actions(driver);

		// Move the tab to the price block so we can get BestBuy to load the price of each element
		for (WebElement currentElement : driver.findElements(By.className("price-block")))
			actions.moveToElement(currentElement);

		actions.perform();
	}
}