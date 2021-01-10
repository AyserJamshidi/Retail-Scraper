package com.ayserjamshidi.retailscrape.searchresults.searcheditem;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AmazonSearchItem extends TemplateSearchItem {
	public AmazonSearchItem(final WebElement searchedItem) {
		this.itemName = this.getFirstElementText(searchedItem, By.className("a-size-large a-spacing-none"));
		System.out.println("Item name == " + this.itemName);
	}

	@Override
	public boolean isAvailable() {
		return false;
	}
}