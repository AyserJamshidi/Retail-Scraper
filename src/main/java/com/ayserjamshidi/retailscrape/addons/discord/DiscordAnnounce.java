package com.ayserjamshidi.retailscrape.addons.discord;

import com.ayserjamshidi.retailscrape.addons.discord.DiscordWebhook;
import com.ayserjamshidi.retailscrape.searchresults.searcheditem.TemplateSearchItem;
import com.ayserjamshidi.retailscrape.addons.discord.DiscordChannel;

import java.util.List;

public class DiscordAnnounce {
	static String neweggLogoUrl = "https://www.parcl.com/files/blog/8%20Online%20Stores%20with%20Cheap%20Tech%20Goods/newegg-logo.png";
	static String bestbuyLogoUrl = "https://pisces.bbystatic.com/image2/BestBuy_US/Gallery/BestBuy_Logo_2020-190616.png";

	public static void announce(final DiscordChannel discordChannel, List<TemplateSearchItem> itemList, boolean isComboList) {
		TemplateSearchItem lastItem = null;
		try {
			final DiscordWebhook webhook = new DiscordWebhook(isComboList ? discordChannel.comboChannel : discordChannel.normalChannel);
			webhook.setUsername("Ace");
			webhook.setAvatarUrl("https://i.imgur.com/wTwIARf.png");
			webhook.setContent(discordChannel.role + " - (" + (itemList.size() >= 10 ? "10" : itemList.size()) + ")");

			for (TemplateSearchItem item : itemList) {
				lastItem = item;
				final DiscordWebhook.EmbedObject messageObject = new DiscordWebhook.EmbedObject();

				messageObject.setColor(discordChannel.embedColor);
				if (item.itemName != null)
					messageObject.setAuthor(item.itemName, item.itemUrl, null);

				if (item.imageUrl != null)
					messageObject.setThumbnail(item.imageUrl);

				if (item.itemUrl != null) {
					messageObject.addField("Product Link", item.itemUrl, true);
					messageObject.setUrl(item.itemUrl);
				}

				if (item.addToCartUrl != null)
					messageObject.addField("Add to Cart Link", item.addToCartUrl, true);

				if (item.itemPrice != null)
					messageObject.addField("Price", item.itemPrice, true);

				if (item.shippingCost != null)
					messageObject.addField("Shipping Cost", item.shippingCost, true);

				if (item.promotion != null && item.promotion.length() > 0)
					messageObject.setFooter("Promotion: " + item.promotion, null);
				else
					messageObject.setFooter("No promotions included with this purchase.", null);

				webhook.addEmbed(messageObject);
			}
			webhook.execute();
			System.out.println("Sent!");
			Thread.sleep(500); // Need this to avoid getting blocked by Discord
		} catch (Exception e) {
			//System.out.println("An error occurred while sending a message to discord! \n" + item.itemName + "\n");
			error("An error occurred while attempting to send a message to discord for category item " + itemList.get(0).itemName);

			if (lastItem != null) {
				System.out.println("itemName: " + lastItem.itemName);
				System.out.println("imageUrl: " + lastItem.imageUrl);
				System.out.println("itemUrl: " + lastItem.itemUrl);
				System.out.println("addToCartUrl: " + lastItem.addToCartUrl);
				System.out.println("itemPrice: " + lastItem.itemPrice);
				System.out.println("shippingCost: " + lastItem.shippingCost);
				System.out.println("promotion: " + lastItem.promotion);
			}
		}
	}

	public static void error(final String errorMessage) {
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