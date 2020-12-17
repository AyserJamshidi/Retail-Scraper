package com.ayserjamshidi.retailscrape.searchresults.template;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NeweggSearchItem {
	public String productUrl, imageUrl, itemName, price, shippingCost, promotion, addToCartText, messageInformation;

	public NeweggSearchItem(WebElement itemCell) {
		WebElement itemTitle = itemCell.findElement(By.className("item-title"));
		WebElement itemAction = itemCell.findElement(By.className("item-action"));
//		WebElement messageWrapper = getFirstElement(itemCell, By.className("message-information"));

		productUrl = itemTitle.getAttribute("href");
		imageUrl = itemCell.findElement(By.cssSelector(".item-img img")).getAttribute("src");
		itemName = itemTitle.getText();
		price = getFirstElementText(itemAction, By.className("price-current"));
		addToCartText = itemAction.findElement(By.className("item-button-area")).getText();

		promotion = getFirstElementText(itemCell, By.className("item-promo"));
		messageInformation = getFirstElementText(itemCell, By.className("message-wrapper"));
	}

	public boolean isAvailable() {
		if (productUrl.contains("Combo.4208290") || productUrl.contains("N82E16814137595"))
			return false;

		String[] possibleUnavailableTexts = {
				"Sold Out",
				"Auto Notify"
		};

		for (String curUnavailableText : possibleUnavailableTexts) // Check if add to cart button indicates OOS
			if (addToCartText.equalsIgnoreCase(curUnavailableText))
				return false; // The item is out of stock

		if (promotion != null && promotion.contains("OUT OF STOCK")) // Check if promotional text indicates OOS
			return false;

		if (messageInformation != null && messageInformation.contains("out of stock")) // Check if cell-info indicates OOS
			return false;

		return true; // Must be in stock!
	}

	protected String getFirstElementText(WebElement element, By searchType) {
		try {
			return element.findElement(searchType).getText();
		} catch (Exception ex) {
			return null;
		}
//		List<WebElement> foundElements = element.findElements(searchType);
//		return foundElements.size() > 0 ? foundElements.get(0).getText() : null;
	}

	/*public void discordAnnouncement(DiscordChannel channel) {
		try {
			DiscordWebhook webhook = new DiscordWebhook(channel.webhookUrl);
			webhook.setAvatarUrl("https://www.parcl.com/files/blog/8%20Online%20Stores%20with%20Cheap%20Tech%20Goods/newegg-logo.png");
			webhook.setUsername("Newegg");
			webhook.setTts(true);

			DiscordWebhook.EmbedObject messageObject = new DiscordWebhook.EmbedObject();
			messageObject.setAuthor(this.itemName, this.productUrl, null);
//					//.setThumbnail("https://www.parcl.com/files/blog/8%20Online%20Stores%20with%20Cheap%20Tech%20Goods/newegg-logo.png")

			messageObject.addField("Product Link", this.productUrl, true);
			messageObject.addField("Add to Cart Link", "https://secure.newegg.com/Shopping/AddtoCart.aspx?Submit=ADD&ItemList="
					+ this.productUrl.substring((this.productUrl.contains("/p/") ? this.productUrl.lastIndexOf("/p/") + 2 : this.productUrl.lastIndexOf("Combo."))), true);

//			if (this.itemName != null)
//				messageObject.setTitle(this.itemName);
////					.setDescription("This is a description")
			messageObject.setColor(channel.embedColor);

			if (this.price != null)
				messageObject.addField("Price", this.price, false);

			if (this.shippingCost != null)
				messageObject.addField("Shipping Cost", this.shippingCost, true);

////					.addField("3rd Field", "No-Inline", false)

			if (this.imageUrl != null)
				messageObject.setImage(this.imageUrl);

			messageObject.setUrl(this.productUrl);

			if (this.promotion != null && this.promotion.length() > 0)
				messageObject.setFooter("Promotion: " + this.promotion, null);
			else
				messageObject.setFooter("No promotions included with this purchase.", null);

			webhook.addEmbed(messageObject);
			webhook.execute();
		} catch (Exception e) {
			System.out.println("An error occurred while sending a message to discord! \n" + itemName + "\n");
			e.printStackTrace();
		}
	}*/

	/*public void errorAnnouncement(DiscordChannel channel, Exception ex) {
		try {
			DiscordWebhook webhook = new DiscordWebhook(channel.webhookUrl);
//			webhook.setAvatarUrl("https://www.parcl.com/files/blog/8%20Online%20Stores%20with%20Cheap%20Tech%20Goods/newegg-logo.png");
			webhook.setUsername("Error");
			webhook.setTts(true);

			webhook.setContent("Error for item " + itemName + "...\n" + Arrays.toString(ex.getStackTrace()));

			webhook.execute();
		} catch (Exception e) {
			System.out.println("An error occurred while sending a message to discord! \n" + itemName + "\n");
			e.printStackTrace();
		}
	}*/
}