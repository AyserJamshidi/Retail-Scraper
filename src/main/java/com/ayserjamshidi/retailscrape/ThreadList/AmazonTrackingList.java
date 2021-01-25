package com.ayserjamshidi.retailscrape.ThreadList;

public enum AmazonTrackingList {
	AMD_5600X("AMD Ryzen 9 5900X 12-core, 24-Thread Unlocked Desktop Processor Without Cooler",
//			"https://www.amazon.com/gp/offer-listing/B08164VTWH",
			"https://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&id=pcat17071&iht=y&keys=keys&ks=960&list=n&qp=category_facet%3DGPUs%20%2F%20Video%20Graphics%20Cards~abcat0507002&sc=Global&st=rtx%203090&type=page&usc=All%20Categories",
			"https://images-na.ssl-images-amazon.com/images/I/41rLUI4FOAL._SS160_.jpg",
			799.99);

	public String itemName, itemUrl, imageUrl;
	public double itemMSRP;

	AmazonTrackingList(final String itemName, final String itemUrl, final String imageUrl, final double itemMSRP) {
		this.itemName = itemName;
		this.itemUrl = itemUrl;
		this.imageUrl = imageUrl;
		this.itemMSRP = itemMSRP;
	}
}