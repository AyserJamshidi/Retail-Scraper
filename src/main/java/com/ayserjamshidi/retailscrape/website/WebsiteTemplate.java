package com.ayserjamshidi.retailscrape.website;

import org.openqa.selenium.WebElement;

public abstract class WebsiteTemplate {
	public abstract boolean outOfStock(WebElement currentItem);
}
