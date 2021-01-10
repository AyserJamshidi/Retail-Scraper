package com.ayserjamshidi.retailscrape.searchresults.searcheditem;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BestBuySearchedItem extends TemplateSearchItem {
	public BestBuySearchedItem(final WebElement searchedItem) {
		final WebElement imageStuff = getFirstElement(searchedItem, By.className("image-link"));
		isCombo = (imageStuff == null);

		if (!isCombo) {
			this.itemUrl = imageStuff.getAttribute("href");
			this.imageUrl = imageStuff.findElement(By.className("product-image")).getAttribute("src");
			this.itemName = getFirstElementText(searchedItem.findElement(By.className("sku-header")), By.tagName("a"));
			this.imageUrl = imageStuff.findElement(By.className("product-image")).getAttribute("src");
			this.itemButtonText = searchedItem.findElement(By.className("sku-list-item-button")).getText(); //getFirstElementText(searchedItem, By.className("sku-list-item-button"));
			this.promotion = getFirstElementText(searchedItem, By.className("gift-with-purchase-price"));

			final List<WebElement> skuElements = searchedItem.findElements(By.className("sku-value"));
			if (skuElements.size() > 0) {
				final String skuNumber = skuElements.get(skuElements.size() - 1).getText();
				this.itemUrl = "https://api.bestbuy.com/click/-/" + skuNumber + "/pdp";
				this.addToCartUrl = "https://api.bestbuy.com/click/-/" + skuElements.get(skuElements.size() - 1).getText() + "/cart";
			}
		} else {
			final WebElement skuTitle = searchedItem.findElement(By.className("sku-title"));
			this.itemUrl = skuTitle.findElement(By.tagName("a")).getAttribute("href");
			this.itemName = skuTitle.getText();
			this.itemButtonText = this.getFirstElementText(searchedItem, By.className("add-to-cart-button"));
			this.imageUrl = this.getFirstElement(searchedItem, By.className("mp-media-gallery-column")).findElement(By.tagName("img")).getAttribute("src");
		}

//		WebElement priceElement = getFirstElement(searchedItem, By.className("priceView-customer-price"));
//
//		if (priceElement != null)
//			this.itemPrice = priceElement.findElement(By.tagName("span")).getText(); //this.getFirstElementText(priceElement, By.tagName("span"));
//		else
//			System.out.println("PRICE WAS NULL!");
	}

	@Override
	public boolean isAvailable() {
		if (this.itemUrl == null) {
			return false;
		}

		final String[] possibleUnavailableTexts = {
				"Sold Out",
				"Coming Soon",
				"Find a Store",
				"Check Stores"
		};

		// Loop all unavailable texts and check if the website has it
		for (final String curUnavailableText : possibleUnavailableTexts)
			if (this.itemButtonText.contains(curUnavailableText))
				return false;

		return true;
	}

	@Override
	public boolean isCombo() {
		return this.isCombo;
	}
}