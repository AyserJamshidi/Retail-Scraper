package com.ayserjamshidi.retailscrape.searchresults;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import com.ayserjamshidi.retailscrape.addons.discord.DiscordChannel;
import com.ayserjamshidi.retailscrape.TrackingList;

public class AmazonSearch extends WebSearch {
	double maxPriceHikePercent;
	double maxPrice;
	TrackingList itemDetails;

	public AmazonSearch(final String threadName, final DiscordChannel discordChannel, final int MIN_WAIT_SECONDS, final int MAX_WAIT_SECONDS, final TrackingList trackingList) {
		super(threadName, discordChannel, MIN_WAIT_SECONDS, MAX_WAIT_SECONDS, new String[]{trackingList.itemUrl}, By.className("olpOffer"));
		this.maxPriceHikePercent = 0.05;
		this.itemDetails = trackingList;
		this.maxPrice = this.itemDetails.itemMSRP * (1.0 + this.maxPriceHikePercent);
	}

	public boolean itemIsGood(final WebElement element) {
		final WebElement itemCondition = this.getFirstElement(element, By.className("olpCondition"));
		final String itemPrice = this.getFirstElementText(element, By.className("olpOfferPrice"));
		if (itemPrice != null) {
			final double itemPriceActual = Float.parseFloat(itemPrice.replace("$", ""));
			System.out.println("Found price Actual! == " + itemPriceActual);
			if (itemPriceActual >= this.itemDetails.itemMSRP * 0.85 && itemPriceActual <= this.maxPrice) {
				System.out.println("Price good!  Condition == " + (itemCondition != null && itemCondition.getText().equals("New")));
				return itemCondition != null && itemCondition.getText().equals("New");
			}
			System.out.println("Price was too damn high...");
		} else {
			System.out.println("Item price was NULL");
		}
		return false;
	}
}