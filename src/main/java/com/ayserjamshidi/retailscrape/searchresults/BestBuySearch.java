package com.ayserjamshidi.retailscrape.searchresults;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import com.ayserjamshidi.retailscrape.addons.discord.DiscordChannel;

public class BestBuySearch extends WebSearch
{
	public BestBuySearch(final String threadName, final DiscordChannel discordChannel, final int MIN_WAIT_SECONDS, final int MAX_WAIT_SECONDS, final String[] pageUrls) {
		super(threadName, discordChannel, MIN_WAIT_SECONDS, MAX_WAIT_SECONDS, pageUrls, By.className("sku-item"));
		System.out.println(driver.getPageSource());
	}

	@Override
	protected boolean itemIsGood(final WebElement element) {
		return true;
	}
}