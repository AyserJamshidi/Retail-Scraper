package com.ayserjamshidi.retailscrape.searchresults.itemtemplate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AmazonSearchItem extends TemplateSearchItem {
	public AmazonSearchItem(final WebElement searchedItem) {
		itemName = getFirstElementText(searchedItem, By.className("a-size-large a-spacing-none"));
		System.out.println("Item name == " + this.itemName);

		addToCartUrl = "https://amazon.com/somethingsomething?=";

		itemUrl = "ADD ME SOOOOON";//itemTitle.getAttribute("href");

//		itemName = searchedItem.findElement(By.id("olpProductDetails")).findElement(By.className("a-size-large a-spacing-none")).getText();
		itemPrice = getFirstElementText(searchedItem, By.cssSelector(".a-size-large.a-color-price.olpOfferPrice.a-text-bold")); //getFirstElementText(itemAction, By.className("price-current"));
		System.out.println("Found price == " + itemPrice);
		WebElement shippingInformation = getFirstElement(searchedItem, By.className("olpShippingPrice"));
		shippingCost = (shippingInformation != null) ? shippingInformation.getText() : "Free";
		System.out.println("Found shipping == " + shippingCost);
//		itemButtonText = itemAction.findElement(By.className("item-button-area")).getText();
//		promotion = getFirstElementText(searchedItem, By.className("item-promo"));
//		messageInformation = getFirstElementText(searchedItem, By.className("message-wrapper"));

	}

	@Override
	public boolean isAvailable() {
		return false;
	}
}