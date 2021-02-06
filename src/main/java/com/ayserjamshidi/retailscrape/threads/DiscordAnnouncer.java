package com.ayserjamshidi.retailscrape.threads;

import com.ayserjamshidi.retailscrape.RetailConfig;
import com.ayserjamshidi.retailscrape.addons.discord.DiscordChannel;
import com.ayserjamshidi.retailscrape.addons.discord.DiscordWebhook;
import com.ayserjamshidi.retailscrape.searchresults.itemtemplate.TemplateSearchItem;

import java.util.ArrayList;
import java.util.List;

public class DiscordAnnouncer extends Thread {
	private final static String BOT_NAME = "Ace";
	private final static String BOT_AVATAR_URL = "https://i.imgur.com/wTwIARf.png";

	static String neweggLogoUrl = "https://www.parcl.com/files/blog/8%20Online%20Stores%20with%20Cheap%20Tech%20Goods/newegg-logo.png";
	static String bestbuyLogoUrl = "https://pisces.bbystatic.com/image2/BestBuy_US/Gallery/BestBuy_Logo_2020-190616.png";

	public static List<DiscordWebhook> webhookList = new ArrayList<>();

	public DiscordAnnouncer() {
		this.setDaemon(false); // Allows this thread to run even after the main thread has closed.
	}

	@Override
	public void run() {
		System.out.println("[DiscordAnnouncer] - Started.");
		while (!this.isInterrupted()) {
			try {
				if (!webhookList.isEmpty()) {
					DiscordWebhook curWebhook = webhookList.get(0);
					curWebhook.execute();
					webhookList.remove(curWebhook);
					sleep(334);
				}
				sleep(10);
			} catch (Exception ex) {
				queueError("Something happened while attempting to send a discord message!");
				ex.printStackTrace();
			}
		}
	}

	public static void queueAnnounce(final DiscordChannel discordChannel, List<TemplateSearchItem> itemList) {
		TemplateSearchItem inLoopItem = null;

		try {
			boolean isComboList = itemList.get(0).isCombo;

			new DiscordAnnouncer().queueRolePing(discordChannel, itemList.size(), isComboList);

			for (int i = 0, loopAmount = (int) Math.ceil(itemList.size() / 10F); i < loopAmount; i++) {
				DiscordWebhook webhook = new DiscordAnnouncer().setupWebhook(discordChannel, itemList.get(0).isCombo);

				for (int j = 0; j < 10 && !itemList.isEmpty(); j++) { // 10 embeds per webhook
					final DiscordWebhook.EmbedObject messageObject = new DiscordWebhook.EmbedObject();
					messageObject.setColor(discordChannel.embedColor);

					inLoopItem = itemList.get(0);

					if (inLoopItem.itemName != null)
						messageObject.setAuthor(inLoopItem.itemName, inLoopItem.itemUrl, null);
					else
						queueError("We announced an item that has a null name or url, check logs.");

					if (inLoopItem.imageUrl != null)
						messageObject.setThumbnail(inLoopItem.imageUrl);

					if (inLoopItem.itemUrl != null) {
						messageObject.addField("Product Link", inLoopItem.itemUrl, true);
						messageObject.setUrl(inLoopItem.itemUrl);
					}

					if (inLoopItem.addToCartUrl != null)
						messageObject.addField("Add to Cart Link", inLoopItem.addToCartUrl, true);

					if (inLoopItem.itemPrice != null)
						messageObject.addField("Price", inLoopItem.itemPrice, true);

					if (inLoopItem.shippingCost != null)
						messageObject.addField("Shipping Cost", inLoopItem.shippingCost, true);

					if (inLoopItem.promotion != null && inLoopItem.promotion.length() > 0)
						messageObject.setFooter("Promotion: " + inLoopItem.promotion, null);
//					else
//						messageObject.setFooter("No promotions included with this purchase.", null);

					itemList.remove(inLoopItem);
					webhook.addEmbed(messageObject);
				}

				if (!webhook.getEmbeds().isEmpty())
					webhookList.add(webhook);
			}
		} catch (Exception e) {
			System.out.println("\n\nAn error occurred while sending a message to discord!\n");
			if (itemList.size() > 0)
				queueError("An error occurred while attempting to send a message to discord for category item " + itemList.get(0).itemName);

			if (inLoopItem != null) {
				System.out.println("itemName: " + inLoopItem.itemName);
				System.out.println("imageUrl: " + inLoopItem.imageUrl);
				System.out.println("itemUrl: " + inLoopItem.itemUrl);
				System.out.println("addToCartUrl: " + inLoopItem.addToCartUrl);
				System.out.println("itemPrice: " + inLoopItem.itemPrice);
				System.out.println("shippingCost: " + inLoopItem.shippingCost);
				System.out.println("promotion: " + inLoopItem.promotion);
			}

			e.printStackTrace();
		}
	}

	public static void queueError(final String errorMessage) {
		try {
			final DiscordWebhook webhook = new DiscordWebhook(DiscordChannel.ADMIN_ERRORS.channel);
			webhook.setUsername("Error");
			webhook.setContent(DiscordChannel.ADMIN_ERRORS.role + " - " + errorMessage);
			webhookList.add(webhook);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void queueRolePing(DiscordChannel discordChannel, int listSize, boolean isComboList) {
		DiscordWebhook mainWebhook = setupWebhook(discordChannel, isComboList);
		mainWebhook.setContent(discordChannel.role + " - " + listSize);
//		webhookList.add(0, mainWebhook);
		webhookList.add(mainWebhook);
	}

	private DiscordWebhook setupWebhook(DiscordChannel discordChannel, boolean isComboList) {
		DiscordWebhook outputWebhook = new DiscordWebhook();

		if (RetailConfig.TEST_MODE)
			outputWebhook.setUrl(DiscordChannel.ADMIN_ERRORS.channel);
		else
			outputWebhook.setUrl(discordChannel.channel);
//			outputWebhook.setUrl(isComboList ? DiscordChannel.COMBO_USA.channel : discordChannel.channel);

		outputWebhook.setUsername(BOT_NAME);
		outputWebhook.setAvatarUrl(BOT_AVATAR_URL);

		return outputWebhook;
	}
}
