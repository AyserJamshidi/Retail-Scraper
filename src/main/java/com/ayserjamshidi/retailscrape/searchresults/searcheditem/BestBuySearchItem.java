package com.ayserjamshidi.retailscrape.searchresults.searcheditem;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BestBuySearchItem extends TemplateSearchItem {

	WebElement mainElement;

	public BestBuySearchItem(final WebElement mainElement) {
		this.mainElement = mainElement;
		final WebElement imageStuff = getFirstElement(mainElement, By.className("image-link"));
		isCombo = (imageStuff == null);

		if (!isCombo) {
			this.itemUrl = imageStuff.getAttribute("href");
			this.imageUrl = imageStuff.findElement(By.className("product-image")).getAttribute("src");
			this.itemName = getFirstElementText(mainElement.findElement(By.className("sku-header")), By.tagName("a"));
			this.imageUrl = imageStuff.findElement(By.className("product-image")).getAttribute("src");
			this.itemButtonText = mainElement.findElement(By.className("sku-list-item-button")).getText();
			this.promotion = getFirstElementText(mainElement, By.className("gift-with-purchase-price"));

			final List<WebElement> skuElements = mainElement.findElements(By.className("sku-value"));
			if (skuElements.size() > 0) {
				final String skuNumber = skuElements.get(skuElements.size() - 1).getText();
				this.itemUrl = "https://api.bestbuy.com/click/-/" + skuNumber + "/pdp";
				this.addToCartUrl = "https://api.bestbuy.com/click/-/" + skuNumber + "/cart";
			}
		} else {
			final WebElement skuTitle = mainElement.findElement(By.className("sku-title"));
			this.itemUrl = skuTitle.findElement(By.tagName("a")).getAttribute("href");
			this.itemName = skuTitle.getText();
			this.itemButtonText = this.getFirstElementText(mainElement, By.className("add-to-cart-button"));
			this.imageUrl = this.getFirstElement(mainElement, By.className("mp-media-gallery-column")).findElement(By.tagName("img")).getAttribute("src");
		}

		WebElement priceElement = getFirstElement(mainElement, By.className("priceView-customer-price"));

		if (priceElement != null)
			itemPrice = priceElement.findElement(By.tagName("span")).getText(); //this.getFirstElementText(priceElement, By.tagName("span"));
		else
			System.out.println("PRICE WAS NULL!");
	}

	@Override
	public boolean isAvailable() {
		if (this.itemUrl == null)
			return false;

		WebElement test = getFirstElement(mainElement, By.className("fulfillment-fulfillment-summary"));

		if (test != null && test.getText().contains("Sold Out"))
			return false;

		final String[] possibleUnavailableTexts = {
				"Sold Out",
				"Coming Soon",
				"Find a Store",
				"Check Stores",
				"Shop Open-Box"
		};

		// Loop all unavailable text and returns false if the item has it
		for (final String curUnavailableText : possibleUnavailableTexts)
			if (itemButtonText.contains(curUnavailableText))
				return false;

		return true;
	}

	@Override
	public boolean isCombo() {
		return isCombo;
	}
}