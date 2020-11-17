package com.ayserjamshidi.retailscrape.searchitem;

import org.openqa.selenium.WebElement;

public abstract class ItemTemplate {

	public String imageSrc, rating, listingName, promotion, priceBeforeDiscount, currentPrice, shippingPrice, link;

	public ItemTemplate() {
		imageSrc = null;
		rating = null;
		listingName = null;
		promotion = null;
		priceBeforeDiscount = null;
		currentPrice = null;
		shippingPrice = null;
		link = null;
	}

//	public WebItem(WebElement element) {
//		setImageSrc(element.findElement(By.className("item-img")));
//		setItemRating(element.findElement(By.className("item-rating")));
//		setListingName(element.findElement(By.className("item-title")));
//		setInStockThing(element.findElement(By.className("item-promo")));
//		setPriceWas(element.findElement(By.className("price-was")));
//		setPriceCurrent(element.findElement(By.className("price-current")));
//		setPriceShip(element.findElement(By.className("price-ship")));
//	}

	public abstract boolean setImageSrc(WebElement element);

	public abstract boolean setItemRating(WebElement element);

	public abstract boolean setListingName(WebElement element);

	public abstract boolean setPromotion(WebElement element);

	public abstract boolean setPriceBeforeDiscount(WebElement element);

	public abstract boolean setPriceCurrent(WebElement element);

	public abstract boolean setPriceShipping(WebElement element);

	public abstract boolean setLink(WebElement element);

	public abstract String toString();
}