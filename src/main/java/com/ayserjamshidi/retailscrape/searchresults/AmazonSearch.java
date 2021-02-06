package com.ayserjamshidi.retailscrape.searchresults;

import com.ayserjamshidi.retailscrape.RetailScrape;
import com.ayserjamshidi.retailscrape.ThreadList.AmazonTrackingListUSA;
import com.ayserjamshidi.retailscrape.addons.discord.DiscordChannel;
import com.ayserjamshidi.retailscrape.threads.DiscordAnnouncer;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class AmazonSearch extends WebSearch {

    ChromeOptions options = new ChromeOptions();

    final double maxPriceHikePercent = 1.05; // MSRP + 5% extra
    final double minMsrpPercent = 0.70; // MSRP * minMsrpPercent
    double maxPrice;

    public AmazonSearch(final String threadName, final DiscordChannel discordChannel, final AmazonTrackingListUSA amazonTrackingList) {
        super(threadName, discordChannel, 10, 15, new String[]{amazonTrackingList.itemUrl}, By.cssSelector(".a-row.a-spacing-mini.olpOffer"));
        amazonItemDetails = amazonTrackingList;
        maxPrice = amazonItemDetails.itemMSRP * maxPriceHikePercent;
    }

    @Override
    public void run() {
        System.setProperty("webdriver.chrome.driver", "src/main/drivers/chromedriver" + (System.getProperty("os.name").contains("Mac OS") ? "" : ".exe"));
//		options.setCapability("proxy", proxy);

        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        // Disable JS
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.managed_default_content_settings.javascript", 2);
        chromePrefs.put("profile.managed_default_content_settings.images", 2);
        options.setExperimentalOption("prefs", chromePrefs);

        // Setup proxy
        proxy = new Proxy();
        setupProxy();
//		driver = new ChromeDriver(options);

        deleteCookies();

        super.run();
    }

    @Override
    public boolean itemIsValid(final WebElement element) {
        final WebElement itemCondition = getFirstElement(element, By.className("olpCondition"));
        final String itemPrice = getFirstElementText(element, By.className("olpOfferPrice"));

        if (itemPrice != null) {
            final double itemPriceActual = Float.parseFloat(itemPrice.replace("$", "").replace(",", ""));

            if ((itemPriceActual >= amazonItemDetails.itemMSRP * minMsrpPercent) && (itemPriceActual <= maxPrice))
                return itemCondition != null && itemCondition.getText().equals("New");
        } else {
            DiscordAnnouncer.queueError("We got a null price when looking for an Amazon item.");
        }
        return false;
    }

    @Override
    protected void setupProxy() {
        if (RetailScrape.proxyList.size() > 0) {
            super.setupProxy();

            // Chrome shit
            if (driver != null)
                driver.close();

            ChromeOptions options = new ChromeOptions();
            options.setCapability("proxy", proxy);

            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(180, 180));
        }
    }

    @Override
    protected boolean pageReloaded() {
        return driver.getTitle().contains("Buying Choices");
    }

    @Override
    protected void badPageReload() {
        sendMessage("Attempting to land on the offer-listing page for " + amazonItemDetails.name());
        deleteCookies();
    }

    private void deleteCookies() {
        driver.manage().deleteAllCookies();
    }
}