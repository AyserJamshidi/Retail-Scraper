package com.ayserjamshidi.retailscrape.searchresults.itemtemplate;

import com.ayserjamshidi.retailscrape.ThreadList.AmazonTrackingListUSA;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AmazonSearchItem extends TemplateSearchItem {
	public AmazonSearchItem(final WebElement searchedItem, AmazonTrackingListUSA trackedItem) {
		itemName = trackedItem.itemName;
		imageUrl = trackedItem.imageUrl;
		itemUrl =  trackedItem.affiliateUrl;

//		addToCartUrl = "https://amazon.com/somethingsomething?=";

		itemPrice = getFirstElementText(searchedItem, By.cssSelector(".a-size-large.a-color-price.olpOfferPrice.a-text-bold"));
		WebElement shippingInformation = getFirstElement(searchedItem, By.className("olpShippingPrice"));
		shippingCost = (shippingInformation != null) ? shippingInformation.getText() : "Free";
		promotion = "The above are affiliate links. Thanks for supporting us!";
		itemButtonText = searchedItem.findElement(By.name("submit.addToCart")).getText();
//		promotion = getFirstElementText(searchedItem, By.className("item-promo"));
//		messageInformation = getFirstElementText(searchedItem, By.className("message-wrapper"));

	}

	@Override
	public boolean isAvailable() {
		return true;
	}
}