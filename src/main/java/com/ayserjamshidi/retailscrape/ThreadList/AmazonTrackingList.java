package com.ayserjamshidi.retailscrape.ThreadList;

public enum AmazonTrackingList {
	AMD_5950X("AMD Ryzen 9 5950X 16-core, 32-Thread Unlocked Desktop Processor Without Cooler",
			"https://www.amazon.com/gp/offer-listing/B0815Y8J9N",
			"https://amzn.to/3r6w8CZ",
			"https://images-na.ssl-images-amazon.com/images/I/616VM20%2BAzL._AC_SX466_.jpg",
			799.00),
	AMD_5900X("AMD Ryzen 9 5900X 12-core, 24-Thread Unlocked Desktop Processor Without Cooler",
			"https://www.amazon.com/gp/offer-listing/B08164VTWH",
			"https://amzn.to/3r2VOk3",
			"https://images-na.ssl-images-amazon.com/images/I/41rLUI4FOAL._SS160_.jpg",
			549.00),
	AMD_5800X("AMD Ryzen 7 5800X 8-core, 16-Thread Unlocked Desktop Processor Without Cooler Black, XX-Large",
			"https://www.amazon.com/gp/offer-listing/B0815XFSGK",
			"https://amzn.to/3ai9hxt",
			"https://images-na.ssl-images-amazon.com/images/I/61DYLoyNRWL._AC_SX466_.jpg",
			449.00),
	AMD_5600X("AMD Ryzen 5 5600X 6-core, 12-Thread Unlocked Desktop Processor with Wraith Stealth Cooler",
			"https://www.amazon.com/gp/offer-listing/B08166SLDF",
			"https://amzn.to/3tjHdCT",
			"https://images-na.ssl-images-amazon.com/images/I/61vGQNUEsGL._AC_SX466_.jpg",
			299.00),
	;

	public String itemName, itemUrl, affiliateUrl, imageUrl;
	public double itemMSRP;

	AmazonTrackingList(final String itemName, final String itemUrl, final String affiliateUrl, final String imageUrl, final double itemMSRP) {
		this.itemName = itemName;
		this.itemUrl = itemUrl;
		this.affiliateUrl = affiliateUrl;
		this.imageUrl = imageUrl;
		this.itemMSRP = itemMSRP;
	}
}