package com.ayserjamshidi.retailscrape.searchresults;

import com.ayserjamshidi.retailscrape.ThreadList.AmazonTrackingList;
import com.ayserjamshidi.retailscrape.addons.discord.DiscordChannel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AmazonSearch extends WebSearch {
	double maxPriceHikePercent;
	double maxPrice;
	AmazonTrackingList itemDetails;

	public AmazonSearch(final String threadName, final DiscordChannel discordChannel, final AmazonTrackingList amazonTrackingList) {
		super(threadName, discordChannel, 10, 15, new String[]{amazonTrackingList.itemUrl}, By.cssSelector(".a-row.a-spacing-mini.olpOffer") /*By.className("olpOffer")*/);
		maxPriceHikePercent = 0.10;
		itemDetails = amazonTrackingList;
		maxPrice = itemDetails.itemMSRP * (1.0 + maxPriceHikePercent);
	}

	@Override
	public void run() {
//		driver = new HtmlUnitDriver();
		super.run();
	}

	public boolean itemIsValid(final WebElement element) throws Exception {
		final WebElement itemCondition = getFirstElement(element, By.className("olpCondition"));
		final String itemPrice = getFirstElementText(element, By.className("olpOfferPrice"));

		if (itemPrice != null) {
			final double itemPriceActual = Float.parseFloat(itemPrice.replace("$", ""));

			if (itemPriceActual >= itemDetails.itemMSRP * 0.85 && itemPriceActual <= maxPrice) {
				//System.out.println("Price good!  Condition == " + (itemCondition != null && itemCondition.getText().equals("New")));
				return itemCondition != null && itemCondition.getText().equals("New");
			}
			//System.out.println("Price was too damn high...");
		} else {
			throw new Exception("Item price was NULL");
		}
		return false;
	}
}