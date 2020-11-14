package com.ayserjamshidi.retailscrape;

import com.ayserjamshidi.retailscrape.SearchResultItem.NeweggSearchResultItem;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.List;

public class RetailScrape {
	public static void main(String[] args) {
		System.out.println("Initializing...");
		HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER);
		System.out.println("Starting loop.");

//		String searchQuery = "ryzen 3950";
        String searchQuery = "asus 3080";

//		thing();

		while (true) {
			driver.get("https://www.newegg.com/p/pl?d=" + searchQuery.replace(" ", "+") + "&PageSize=96");

			List<WebElement> test = driver.findElementsByClassName("item-container");

			for (WebElement currentItem : test) {
				NeweggSearchResultItem testyItem = new NeweggSearchResultItem(currentItem);

				System.out.println(testyItem);
//				String itemName = currentItem.findElement(By.className("item-title")).getText();
//
//				if (itemName.toLowerCase().contains("ryzen")) {
//					WebElement itemStock = currentItem.findElement(By.className("item-promo"));
//					System.out.println(currentItem.findElement(By.className("item-title")).getText());
//
//					if (itemStock != null) {
//						if (!outOfStock(currentItem)) {
//							System.out.println("In stock!");
//							try {
//								//Desktop.getDesktop().browse(new URI("http://www.example.com"));
//							} catch (Exception e) {
//								e.printStackTrace();
//							}
//						} else {
//							System.out.println("Out of stock... :(");
//						}
//					}
//				}


				System.out.println();
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("--------------------------------------------------");
		}
	}

	/*public static void thing() {
		try {
			com.ayserjamshidi.retailscrape.DiscordWebhook webhook = new com.ayserjamshidi.retailscrape.DiscordWebhook("https://discord.com/api/webhooks/774850994286624768/-4obrF_fFMdKvsOfJNLMyPa8e2bCa1ggw3X2Rt_QCNhYwhaIl6JBTTYS1Ho22-u5Sl_l");
			webhook.setContent("Any message!");
			webhook.setAvatarUrl("https://your.awesome/image.png");
			webhook.setUsername("Custom Usernames!");
			webhook.setTts(true);
			webhook.addEmbed(new com.ayserjamshidi.retailscrape.DiscordWebhook.EmbedObject()
					.setTitle("Title")
					.setDescription("This is a description")
					.setColor(Color.RED)
					.addField("1st Field", "Inline", true)
					.addField("2nd Field", "Inline", true)
					.addField("3rd Field", "No-Inline", false)
					.setThumbnail("https://kryptongta.com/images/kryptonlogo.png")
					.setFooter("Footer text", "https://kryptongta.com/images/kryptonlogodark.png")
					.setImage("https://kryptongta.com/images/kryptontitle2.png")
					.setAuthor("Author Name", "https://kryptongta.com", "https://kryptongta.com/images/kryptonlogowide.png")
					.setUrl("https://kryptongta.com"));
			webhook.addEmbed(new com.ayserjamshidi.retailscrape.DiscordWebhook.EmbedObject()
					.setDescription("Just another added embed object!"));
			webhook.execute(); //Handle exception
		} catch (Exception e) {
			System.out.println("An error occured while sending a message to discord!\n" + e);
		}
	}*/

	public static boolean outOfStock(WebElement currentItem) {
		WebElement itemStock = currentItem.findElement(By.className("item-promo"));

		return itemStock.getText().toLowerCase().contains("out of stock");
	}
}