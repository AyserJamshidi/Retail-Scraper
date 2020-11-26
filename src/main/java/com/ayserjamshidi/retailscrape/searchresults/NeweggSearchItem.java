package com.ayserjamshidi.retailscrape.searchresults;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class NeweggSearchItem extends WebSearchItem {

	WebElement mainElement;

	public NeweggSearchItem(WebElement element) {
//		super(); // Ensure all variables are initialized to null string
		mainElement = element;
		final WebElement itemDetails = element.findElement(By.className("item-info"));
		final WebElement itemPricing = element.findElement(By.className("item-action"));

		setImageSrc(By.cssSelector(".item-img img"));
//		setImageSrc(element.findElement(By.cssSelector(".item-img img")));

//		setLink(element.findElement(By.className("item-img")));

		setItemRating(By.className("item-rating"));
//		setItemRating(itemDetails.findElement(By.className("item-rating")));

//		setListingName(itemDetails.findElement(By.className("item-title")));

		/*
		 * Unfortunately we have to use a try/catch to check for item promotions as Selenium doesn't seem to have
		 * a time-efficient way of checking if an element exists.  They all consist of halting a thread until a
		 * function times out after n seconds, which is stupid, or to use findElements and check its length or
		 * something, which is too costly and too long when looking for a lot of items.
		 */
//		try {
//			setPromotion(itemDetails.findElement(By.className("item-promo")));
//		} catch (Exception e) {
//			this.promotion = "No promotions.";
//		}

//		setPriceBeforeDiscount(itemPricing.findElement(By.className("price-was")));
//		setPriceCurrent(itemPricing.findElement(By.className("price-current")));
//		setPriceShipping(itemPricing.findElement(By.className("price-ship")));
	}

	@Override
	public void setImageSrc(By searchType) throws NoSuchElementException {
		this.imageSrc = mainElement.findElement(searchType).getAttribute("src");
	}

	@Override
	public void setItemRating(By searchType) {
		this.rating = mainElement.findElement(searchType).getAttribute("title");
//		this.rating = element.getAttribute("title").substring("Rating + ".length());
	}

	@Override
	public void setListingName(By searchType) {
//		if (element != null) {
//			this.listingName = element.getText();
//			return true;
//		}
//
//		this.listingName = null;
//		return false;
	}

	@Override
	public void setPromotion(By searchType) {
//		if (element != null) {
//			this.promotion = element.getText();
//			return true;
//		}
//
//		this.promotion = null;
//		return false;
	}

	@Override
	public void setPriceBeforeDiscount(By searchType) {
//		if (element != null) {
//			this.priceBeforeDiscount = element.getText();
//			return true;
//		}
//
//		this.priceBeforeDiscount = null;
//		return false;
	}

	@Override
	public void setPriceCurrent(By searchType) {
//		if (element != null) {
//			this.currentPrice = element.getText();
//			return true;
//		}
//
//		this.currentPrice = null;
//		return false;
	}

	@Override
	public void setPriceShipping(By searchType) {
//		if (element != null) {
//			this.shippingPrice = element.getText();
//			return true;
//		}
//
//		this.shippingPrice = null;
//		return false;
	}

	@Override
	public void setLink(By searchType) {
//		if (element != null) {
//			this.link = element.getAttribute("href");
//			return true;
//		}
//
//		this.link = null;
//		return false;
	}

	public String toString() {
		return "Image Link: " + this.imageSrc +
				"\nProduct Link: " + this.link +
				"\nRating: " + this.rating +
				"\nListing Name: " + this.listingName +
				"\nPromotion Thing: " + this.promotion +
				"\nPrice before discount: " + this.priceBeforeDiscount +
				"\nCurrent price: " + this.currentPrice +
				"\nShipping price: " + this.shippingPrice;
	}
}