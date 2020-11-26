package com.ayserjamshidi.retailscrape.searchresults;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public abstract class WebSearchItem {

	public String imageSrc, rating, listingName, promotion, priceBeforeDiscount, currentPrice, shippingPrice, link;

//	public ItemTemplate() {
//		imageSrc = "null";
//		rating = "null";
//		listingName = "null";
//		promotion = "null";
//		priceBeforeDiscount = "null";
//		currentPrice = "null";
//		shippingPrice = "null";
//		link = "null";
//	}

//	public WebItem(WebElement element) {
//		setImageSrc(element.findElement(By.className("item-img")));
//		setItemRating(element.findElement(By.className("item-rating")));
//		setListingName(element.findElement(By.className("item-title")));
//		setInStockThing(element.findElement(By.className("item-promo")));
//		setPriceWas(element.findElement(By.className("price-was")));
//		setPriceCurrent(element.findElement(By.className("price-current")));
//		setPriceShip(element.findElement(By.className("price-ship")));
//	}

	public abstract void setImageSrc(By searchType) throws NoSuchElementException;

	public abstract void setItemRating(By searchType);

	public abstract void setListingName(By searchType);

	public abstract void setPromotion(By searchType);

	public abstract void setPriceBeforeDiscount(By searchType);

	public abstract void setPriceCurrent(By searchType);

	public abstract void setPriceShipping(By searchType);

	public abstract void setLink(By searchType);

	public abstract String toString();
}