package com.ayserjamshidi.retailscrape.SearchResultItem;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NeweggSearchResultItem extends WebItem {

	//System.out.println(element.findElement(By.xpath("div[@class='featured-box cloumnsize1']")));

	public NeweggSearchResultItem(WebElement element) {
		super(); // Ensure all variables are initialized to null
		final WebElement itemDetails = element.findElement(By.className("item-info"));
		final WebElement itemPricing = element.findElement(By.className("item-action"));

		setImageSrc(element.findElement(By.cssSelector(".item-img img")));
		setItemRating(itemDetails.findElement(By.className("item-rating")));
		setListingName(itemDetails.findElement(By.className("item-title")));

		/*
		 * Unfortunately we have to use a try/catch to check for item promotions as Selenium doesn't seem to have
		 * a time-efficient way of checking if an element exists.  They all consist of halting a thread until a
		 * function times out after n seconds, which is stupid, or to use findElements and check its length or
		 * something, which is too costly and too long when looking for a lot of items.
		 */
		try {
			setPromotion(itemDetails.findElement(By.className("item-promo")));
		} catch (Exception e) {
			this.promotion = "No promotions.";
		}

		setPriceBeforeDiscount(itemPricing.findElement(By.className("price-was")));
		setPriceCurrent(itemPricing.findElement(By.className("price-current")));
		setPriceShipping(itemPricing.findElement(By.className("price-ship")));
	}

	@Override
	public boolean setImageSrc(WebElement element) {
		if (element != null) {
			//System.out.println(element.toString());
			this.imageSrc = element.getAttribute("src");
			return true;
		}

		this.imageSrc = null;
		return false;
	}

	//System.out.println(element.findElement(By.xpath("div[@class='featured-box cloumnsize1']")));
	@Override
	public boolean setItemRating(WebElement element) {
		if (element != null) {
			this.rating = element.getAttribute("title").substring("Rating + ".length());
			return true;
		}

		this.rating = null;
		return false;
	}

	@Override
	public boolean setListingName(WebElement element) {
		if (element != null) {
			this.listingName = element.getText();
			return true;
		}

		this.listingName = null;
		return false;
	}

	@Override
	public boolean setPromotion(WebElement element) {
		if (element != null) {
			this.promotion = element.getText();
			return true;
		}

		this.promotion = null;
		return false;
	}

	@Override
	public boolean setPriceBeforeDiscount(WebElement element) {
		if (element != null) {
			this.priceBeforeDiscount = element.getText();
			return true;
		}

		this.priceBeforeDiscount = null;
		return false;
	}

	@Override
	public boolean setPriceCurrent(WebElement element) {
		if (element != null) {
			this.currentPrice = element.getText();
			return true;
		}

		this.currentPrice = null;
		return false;
	}

	@Override
	public boolean setPriceShipping(WebElement element) {
		if (element != null) {
			this.shippingPrice = element.getText();
			return true;
		}

		this.shippingPrice = null;
		return false;
	}

	public String toString() {

		return "Image Link: " + this.imageSrc +
				"\nRating: " + this.rating +
				"\nListing Name: " + this.listingName +
				"\nPromotion Thing: " + this.promotion +
				"\nPrice before discount: " + this.priceBeforeDiscount +
				"\nCurrent price: " + this.currentPrice +
				"\nShipping price: " + this.shippingPrice;
	}
}