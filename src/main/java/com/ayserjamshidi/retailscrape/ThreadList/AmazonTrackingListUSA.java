package com.ayserjamshidi.retailscrape.ThreadList;

public enum AmazonTrackingListUSA {
    // Nvidia GPU

    // 3090
    NVIDIA_3090_ASUS_TUF_OC_USA("ASUS TUF Gaming NVIDIA GeForce RTX 3090 OC Edition Graphics Card- PCIe 4.0, 24GB GDDR6X, HDMI 2.1, DisplayPort 1.4a, Dual Ball Fan Bearings",
            "https://www.amazon.com/gp/offer-listing/B08HJGNJ81", "https://amzn.to/3oVrNkG",
            "https://images-na.ssl-images-amazon.com/images/I/81XHNWut5WL._AC_SX679_.jpg",
            1839.99),

    // 3070
    NVIDIA_3070_ASUS_DUAL_OC_USA("ASUS Dual NVIDIA GeForce RTX 3070 OC Edition Gaming Graphics Card (PCIe 4.0, 8GB GDDR6 Memory, HDMI 2.1, DisplayPort 1.4a, Axial-tech Fan Design, Dual BIOS, Protective Backplate, GPU Tweak II)",
            "https://www.amazon.com/gp/offer-listing/B08L8LG4M3", "https://amzn.to/3cMJgcs",
            "https://images-na.ssl-images-amazon.com/images/I/81Bfni5AVdL._AC_SX679_.jpg",
            599.99),
    NVIDIA_3070_ASUS_TUF_OC_USA("ASUS TUF Gaming NVIDIA GeForce RTX 3070 OC Edition Graphics Card- PCIe 4.0, 8GB GDDR6, HDMI 2.1 , DisplayPort 1.4a, Dual Ball Fan Bearings",
            "https://www.amazon.com/gp/offer-listing/B08L8KC1J7", "https://amzn.to/3jmYFlb",
            "https://images-na.ssl-images-amazon.com/images/I/81fm-uRTN4L._AC_SX679_.jpg",
            599.99),


    // AMD CPU
    AMD_5950X("AMD Ryzen 9 5950X 16-core, 32-Thread Unlocked Desktop Processor Without Cooler",
            "https://www.amazon.com/gp/offer-listing/B0815Y8J9N", "https://amzn.to/3r6w8CZ",
            "https://images-na.ssl-images-amazon.com/images/I/616VM20%2BAzL._AC_SX466_.jpg",
            799.00),
    AMD_5900X("AMD Ryzen 9 5900X 12-core, 24-Thread Unlocked Desktop Processor Without Cooler",
            "https://www.amazon.com/gp/offer-listing/B08164VTWH", "https://amzn.to/3r2VOk3",
            "https://images-na.ssl-images-amazon.com/images/I/41rLUI4FOAL._SS160_.jpg",
            549.00),
    AMD_5800X("AMD Ryzen 7 5800X 8-core, 16-Thread Unlocked Desktop Processor Without Cooler Black, XX-Large",
            "https://www.amazon.com/gp/offer-listing/B0815XFSGK", "https://amzn.to/3ai9hxt",
            "https://images-na.ssl-images-amazon.com/images/I/61DYLoyNRWL._AC_SX466_.jpg",
            449.00),
    AMD_5600X("AMD Ryzen 5 5600X 6-core, 12-Thread Unlocked Desktop Processor with Wraith Stealth Cooler",
            "https://www.amazon.com/gp/offer-listing/B08166SLDF", "https://amzn.to/3tjHdCT",
            "https://images-na.ssl-images-amazon.com/images/I/61vGQNUEsGL._AC_SX466_.jpg",
            299.00),

    // Test
    SAMSUNG_G9("AMD Ryzen 5 5600X 6-core, 12-Thread Unlocked Desktop Processor with Wraith Stealth Cooler",
            "https://www.amazon.com/gp/offer-listing/B088HH6LW5", "https://amzn.to/3rzmqJP",
            "https://images-na.ssl-images-amazon.com/images/I/81r8JazRcoL._AC_SX450_.jpg",
            1050.00),
    ;

    public String itemName, itemUrl, affiliateUrl, imageUrl;
    public double itemMSRP;

    AmazonTrackingListUSA(final String itemName, final String itemUrl, final String affiliateUrl, final String imageUrl, final double itemMSRP) {
        this.itemName = itemName;
        this.itemUrl = itemUrl;
        this.affiliateUrl = affiliateUrl;
        this.imageUrl = imageUrl;
        this.itemMSRP = itemMSRP;
    }
}