package com.ayserjamshidi.retailscrape;

public enum TrackingList {
    AMD_5600X("https://www.amazon.com/gp/offer-listing/B08164VTWH", 299.99);

    public String itemUrl;
    public double itemMSRP;

    TrackingList(final String itemUrl, final double itemMSRP) {
        this.itemUrl = itemUrl;
        this.itemMSRP = itemMSRP;
    }
}