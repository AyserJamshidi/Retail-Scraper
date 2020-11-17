package com.ayserjamshidi.retailscrape;

import com.ayserjamshidi.retailscrape.website.Newegg;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class RetailScrape {
	public static void main(String[] args) {
		System.out.println("Initializing...");
		HtmlUnitDriver driver = new HtmlUnitDriver(false);
		System.out.println("Starting loop.");

		Newegg neweggAmdCpu = new Newegg();
		Thread neweggAmdCpuThread = new Thread(() -> neweggAmdCpu.run("AMD CPU",
				"https://www.newegg.com/p/pl?N=50001028%20100007671%20601359154&Manufactory=1028"));

		Newegg neweggNvidiaGpu = new Newegg();
		Thread neweggNvidiaGpuThread = new Thread(() -> neweggAmdCpu.run("NVIDIA GPU",
				"https://www.newegg.com/p/pl?d=asus+3080&PageSize=96&N=100007709&isdeptsrh=1"));

		neweggAmdCpuThread.start();
		neweggNvidiaGpuThread.start();

		while(neweggAmdCpuThread.isAlive() || neweggNvidiaGpuThread.isAlive()) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("All threads are not alive!");
//		String searchQuery = "ryzen 3950";

//		String searchQuery = "asus 3080";

//		thing();

/*		while (true) {
			driver.get("https://www.newegg.com/p/pl?d=" + searchQuery.replace(" ", "+") + "&PageSize=96&N=100007709&isdeptsrh=1");
//			driver.get("https://www.newegg.com/p/pl?N=100007709%20601357282&PageSize=96");

			List<WebElement> test = driver.findElementsByClassName("item-container");

			for (WebElement currentItem : test) {
				NeweggSearchResultItem testyItem = new NeweggSearchResultItem(currentItem);

				if (!testyItem.promotion.equals("OUT OF STOCK")) {
					//System.out.println(testyItem);
					try {
						//System.out.println("OPENING THIS BITCH");
						Desktop.getDesktop().browse(new URI(testyItem.link.replaceAll(" ", "%20")));
						System.out.println("Found " + testyItem.listingName);
						System.out.println("Stopping, rerun me if you want to find more items.");
						System.exit(0);
					} catch (Exception e) {
						System.out.println("Error occurred while trying to open " + testyItem.listingName + "\n\n" + e);
					}
				} else {
					System.out.println(testyItem.listingName + "is OOS, skipping...");
				}

				System.out.println();
			}

			try {
				System.out.println("Sleeping for 3 seconds before refreshing page...");
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("\n--------------------------------------------------\n");
		}
	}*/

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
		}*/
	}

	/*public static boolean outOfStock(WebElement currentItem) {
		WebElement itemStock = currentItem.findElement(By.className("item-promo"));

		return itemStock.getText().toLowerCase().contains("out of stock");
	}*/
}