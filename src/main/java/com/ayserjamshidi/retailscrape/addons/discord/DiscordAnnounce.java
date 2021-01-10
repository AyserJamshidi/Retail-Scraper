package com.ayserjamshidi.retailscrape.addons.discord;

import com.ayserjamshidi.retailscrape.addons.discord.DiscordWebhook;
import com.ayserjamshidi.retailscrape.searchresults.searcheditem.TemplateSearchItem;
import com.ayserjamshidi.retailscrape.addons.discord.DiscordChannel;

public class DiscordAnnounce {
	String neweggLogoUrl = "https://www.parcl.com/files/blog/8%20Online%20Stores%20with%20Cheap%20Tech%20Goods/newegg-logo.png";
	String bestbuyLogoUrl = "https://pisces.bbystatic.com/image2/BestBuy_US/Gallery/BestBuy_Logo_2020-190616.png";

	public static void announce(final DiscordChannel discordChannel, final TemplateSearchItem item, final boolean isCombo) {
		try {
			final DiscordWebhook webhook = new DiscordWebhook(isCombo ? discordChannel.comboChannel : discordChannel.normalChannel);
			webhook.setUsername("Ace");
			webhook.setContent(discordChannel.role);
			final DiscordWebhook.EmbedObject messageObject = new DiscordWebhook.EmbedObject();
			messageObject.setColor(discordChannel.embedColor);
			messageObject.setAuthor(item.itemName, item.itemUrl, null);
			messageObject.setThumbnail(item.imageUrl);
			if (item.itemUrl != null)
				messageObject.addField("Product Link", item.itemUrl, true);

			if (item.addToCartUrl != null)
				messageObject.addField("Add to Cart Link", item.addToCartUrl, true);

			if (item.itemPrice != null)
				messageObject.addField("Price", item.itemPrice, true);

			if (item.shippingCost != null)
				messageObject.addField("Shipping Cost", item.shippingCost, true);
			if (item.itemUrl != null)
				messageObject.setUrl(item.itemUrl);

			if (item.promotion != null && item.promotion.length() > 0)
				messageObject.setFooter("Promotion: " + item.promotion, null);
			else
				messageObject.setFooter("No promotions included with this purchase.", null);

			webhook.addEmbed(messageObject);
			webhook.execute();
		} catch (Exception e) {
			System.out.println("An error occurred while sending a message to discord! \n" + item.itemName + "\n");
			e.printStackTrace();
		}
	}

	public void error(final String errorMessage) {
		try {
			final DiscordWebhook webhook = new DiscordWebhook(DiscordChannel.ADMIN_ERRORS.normalChannel);
			webhook.setUsername("Error");
			webhook.setContent(DiscordChannel.ADMIN_ERRORS.role + " - " + errorMessage);
			webhook.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}