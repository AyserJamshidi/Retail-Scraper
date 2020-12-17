package com.ayserjamshidi.retailscrape.website;

import com.ayserjamshidi.retailscrape.addons.discord.DiscordChannel;
import com.ayserjamshidi.retailscrape.addons.discord.DiscordWebhook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.util.List;

public abstract class WebItem implements Runnable {

	//	WebDriver driver;
	 ChromeDriver driver;
//	HtmlUnitDriver driver;

	//	HtmlUnitDriver driver;
	public String threadTitle;
	String pageUrl, price, shippingCost, promotion, imageSrc;

	public WebItem() {
//		driver = new HtmlUnitDriver();
		driver = new ChromeDriver();
	}

	public void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void discordAnnouncement(DiscordChannel channel) {
		try {
			DiscordWebhook webhook = new DiscordWebhook(channel.normalChannel);
			webhook.setAvatarUrl("https://www.parcl.com/files/blog/8%20Online%20Stores%20with%20Cheap%20Tech%20Goods/newegg-logo.png");
			webhook.setUsername("Newegg");
			webhook.setTts(true);

			webhook.setContent("@everyone");
			DiscordWebhook.EmbedObject messageObject = new DiscordWebhook.EmbedObject();
			if (this.threadTitle != null)
				messageObject.setAuthor(this.threadTitle, this.pageUrl, null);
//					//.setThumbnail("https://www.parcl.com/files/blog/8%20Online%20Stores%20with%20Cheap%20Tech%20Goods/newegg-logo.png")

			messageObject.addField("Product Link", this.pageUrl, true);
			messageObject.addField("Add to Cart Link", "https://secure.newegg.com/Shopping/AddtoCart.aspx?Submit=ADD&ItemList="
					+ this.pageUrl.substring((this.pageUrl.contains("/p/") ? this.pageUrl.lastIndexOf("/p/") + 2 : this.pageUrl.lastIndexOf("Combo."))), true);

//			if (this.itemName != null)
//				messageObject.setTitle(this.itemName);
////					.setDescription("This is a description")
			messageObject.setColor(Color.RED);

			if (this.price != null)
				messageObject.addField("Price", this.price, false);

			if (this.shippingCost != null)
				messageObject.addField("Shipping Cost", this.shippingCost, true);

////					.addField("3rd Field", "No-Inline", false)

			if (this.imageSrc != null)
				messageObject.setImage(this.imageSrc);

			messageObject.setUrl(this.pageUrl);

			if (this.promotion != null && this.promotion.length() > 0)
				messageObject.setFooter("Promotion: " + this.promotion, null);

			webhook.addEmbed(messageObject);
			webhook.execute();
		} catch (Exception e) {
			System.out.println("An error occurred while sending a message to discord!\n" + e);
		}
	}

	public WebElement getFirstElement(By searchType) {
		List<WebElement> foundElements = driver.findElements(searchType);
		return foundElements.size() > 0 ? foundElements.get(0) : null;
	}
}
