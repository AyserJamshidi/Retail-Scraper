package com.ayserjamshidi.retailscrape.addons.discord.announcing;

import com.ayserjamshidi.retailscrape.addons.discord.DiscordChannel;
import com.ayserjamshidi.retailscrape.addons.discord.DiscordWebhook;
import com.ayserjamshidi.retailscrape.searchresults.template.NeweggSearchItem;

public class DiscordSenderTemplate {
	public void announce(DiscordChannel discordChannel, NeweggSearchItem item, boolean isCombo) {
		try {
			DiscordWebhook webhook = new DiscordWebhook(isCombo ? discordChannel.comboChannel : discordChannel.normalChannel);
			webhook.setAvatarUrl("https://www.parcl.com/files/blog/8%20Online%20Stores%20with%20Cheap%20Tech%20Goods/newegg-logo.png");
			webhook.setUsername("Newegg");
//			webhook.setTts(true);
			webhook.setContent(discordChannel.role);

			DiscordWebhook.EmbedObject messageObject = new DiscordWebhook.EmbedObject();
			messageObject.setAuthor(item.itemName, item.productUrl, null);

			messageObject.addField("Product Link", item.productUrl, true);

			String purchaseLink = "https://secure.newegg.com/Shopping/AddtoCart.aspx?Submit=ADD&ItemList=";

			if (isCombo)
				purchaseLink += item.productUrl.substring(item.productUrl.lastIndexOf("Combo."));
			else {
				int pIndex = item.productUrl.indexOf("/p/");
				purchaseLink += item.productUrl.substring(pIndex + 3, item.productUrl.indexOf("?", pIndex));
			}

			messageObject.addField("Add to Cart Link", purchaseLink,true);

			messageObject.setColor(discordChannel.embedColor);

			if (item.price != null)
				messageObject.addField("Price", item.price, false);

			if (item.shippingCost != null)
				messageObject.addField("Shipping Cost", item.shippingCost, true);

			if (item.imageUrl != null)
				messageObject.setImage(item.imageUrl);

			messageObject.setUrl(item.productUrl);

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

	public void error(String errorMessage) {
		try {
			DiscordWebhook webhook = new DiscordWebhook(DiscordChannel.ADMIN_ERRORS.normalChannel);
			webhook.setUsername("Error");
			webhook.setContent(DiscordChannel.ADMIN_ERRORS.role + " - " + errorMessage);

			webhook.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}