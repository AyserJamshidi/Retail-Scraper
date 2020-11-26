package com.ayserjamshidi.retailscrape.website;

import com.ayserjamshidi.retailscrape.DiscordChannel;
import com.ayserjamshidi.retailscrape.addons.DiscordWebhook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.awt.*;
import java.util.List;

public abstract class WebItem implements Runnable {

	//	WebDriver driver;
	HtmlUnitDriver driver;
	public String threadTitle;
	String pageUrl, price, shippingCost, promotion, imageSrc;

	public WebItem() {
		driver = new HtmlUnitDriver();
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
//			DiscordWebhook webhook = new DiscordWebhook("https://discord.com/api/webhooks/774850994286624768/-4obrF_fFMdKvsOfJNLMyPa8e2bCa1ggw3X2Rt_QCNhYwhaIl6JBTTYS1Ho22-u5Sl_l");
//			DiscordWebhook webhook = new DiscordWebhook("https://discord.com/api/webhooks/780002736581378088/w9doWBCicgG6Bgj_tsfevNym4QwMcJQ9Kl8g85JA0yuPxGwrCh-mR9d2Po0oek0xjajc");
			DiscordWebhook webhook = new DiscordWebhook(channel.webhookUrl);
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

			if (!this.promotion.equals("null"))
				messageObject.setFooter("Promotion: " + this.promotion, null);

			webhook.addEmbed(messageObject);
			webhook.execute();
		} catch (Exception e) {
			System.out.println("An error occurred while sending a message to discord!\n" + e);
		}
	}

	public WebElement getFirstElement(By searchType) {
		List<WebElement> thingy = driver.findElements(searchType);
		return thingy.size() > 0 ? thingy.get(0) : null;
	}
}
