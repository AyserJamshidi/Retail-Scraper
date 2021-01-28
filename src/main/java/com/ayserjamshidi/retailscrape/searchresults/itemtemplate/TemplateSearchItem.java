package com.ayserjamshidi.retailscrape.searchresults.itemtemplate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class TemplateSearchItem {
	public String itemUrl;
	public String addToCartUrl;
	public String imageUrl;
	public String itemName;
	public String itemPrice;
	public String shippingCost;
	public String promotion;
	public String itemButtonText;
	public String messageInformation;
	public boolean isCombo;

	public TemplateSearchItem() {
		this.itemUrl = null;
		this.addToCartUrl = null;
		this.imageUrl = null;
		this.itemName = null;
		this.itemPrice = null;
		this.shippingCost = null;
		this.promotion = null;
		this.itemButtonText = null;
		this.messageInformation = null;
		this.isCombo = false;
	}

	public abstract boolean isAvailable();

	protected boolean isCombo() {
		return false;
	}

	protected String getFirstElementText(final WebElement element, final By searchType) {
		try {
			return element.findElement(searchType).getText();
		} catch (Exception ex) {
			return null;
		}
	}

	protected WebElement getFirstElement(final WebElement element, final By searchType) {
		try {
			return element.findElement(searchType);
		} catch (Exception ex) {
			return null;
		}
	}
}