package com.ayserjamshidi.retailscrape.searchresults.searcheditem;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NeweggSearchItem extends TemplateSearchItem {
	public String promotion;
	public String messageInformation;

	public NeweggSearchItem(final WebElement searchedItem) {
		this.addToCartUrl = "https://secure.newegg.com/Shopping/AddtoCart.aspx?Submit=ADD&ItemList=";
		final WebElement itemTitle = searchedItem.findElement(By.className("item-title"));
		final WebElement itemAction = searchedItem.findElement(By.className("item-action"));

		this.itemUrl = itemTitle.getAttribute("href").replace(" ", "%20");
		this.isCombo = this.itemUrl.contains("ComboDealDetails");
		this.imageUrl = searchedItem.findElement(By.cssSelector(".item-img img")).getAttribute("src").replace(" ", "%20");
		this.itemName = itemTitle.getText();

		this.itemPrice = getFirstElementText(itemAction, By.className("price-current"));
		if (this.itemPrice != null)
			this.itemPrice = this.itemPrice.substring(0, itemPrice.indexOf(' '));

		this.itemButtonText = itemAction.findElement(By.className("item-button-area")).getText();
		this.promotion = getFirstElementText(searchedItem, By.className("item-promo"));
		this.messageInformation = getFirstElementText(searchedItem, By.className("message-wrapper"));

		if (this.isCombo) {
			this.addToCartUrl += this.itemUrl.substring(this.itemUrl.lastIndexOf("Combo."));
		} else {
			final String identifier = "/p/";
			this.addToCartUrl += this.itemUrl.substring(this.itemUrl.indexOf(identifier) + identifier.length(), this.itemUrl.indexOf(63));
		}
	}

	@Override
	public boolean isAvailable() {
		if (this.itemUrl == null)
			return false;

		final String[] possibleUnavailableTexts = {
				"Sold Out",
				"Auto Notify"
		};

		for (final String curUnavailableText : possibleUnavailableTexts)
			if (itemButtonText.equalsIgnoreCase(curUnavailableText))
				return false;

		// Wtf is this
		return (promotion == null || !promotion.contains("OUT OF STOCK")) && (messageInformation == null || !messageInformation.contains("out of stock"));
	}

	@Override
	public boolean isCombo() {
		return this.isCombo;
	}
}