package com.ayserjamshidi.retailscrape;

import com.ayserjamshidi.retailscrape.searchresults.BestBuySearch;
import com.ayserjamshidi.retailscrape.searchresults.NeweggSearch;
import com.ayserjamshidi.retailscrape.addons.discord.DiscordChannel;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RetailScrape {
	static final int MIN_WAIT_TIME = 10;
	static final int MAX_WAIT_TIME = 15;

	public static void main(final String[] args) {
//		Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
//		Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
//		Logger.getLogger("org.apache.http").setLevel(Level.OFF);

		System.out.println("Creating threads...");

		final Thread[] itemSearchList = {
//				new NeweggSearch("Nvidia 3090 - Newegg USA", DiscordChannel.NVIDIA_3090_USA, MIN_WAIT_TIME, MAX_WAIT_TIME, new String[]{
//						"https://www.newegg.com/p/pl?d=RTX%203090&N=4814%208000%20100006662&PageSize=96",
//						"https://www.newegg.com/p/pl?d=RTX%203090&N=4814%20100006662%208000"
//				}),
//				new NeweggSearch("Nvidia 3080 - Newegg USA", DiscordChannel.NVIDIA_3080_USA, MIN_WAIT_TIME, MAX_WAIT_TIME, new String[]{
//						"https://www.newegg.com/p/pl?d=RTX%203080&N=8000%208000%20100006662%204021%204022&PageSize=96",
//						"https://www.newegg.com/p/pl?d=RTX%203080&N=8000%208000%20100006662&PageSize=96",
//						"https://www.newegg.com/p/pl?d=RTX%203080&N=4022%20100006662%204021%204814%208000%204841",
//						"https://www.newegg.com/p/pl?d=RTX%203080&N=100006662%208000%204021%204841%204022%204814"
//				}),
//				new NeweggSearch("Nvidia 3070 - Newegg USA", DiscordChannel.NVIDIA_3070_USA, MIN_WAIT_TIME, MAX_WAIT_TIME, new String[]{
//						"https://www.newegg.com/p/pl?d=RTX%203070&N=8000%208000%20100006662&PageSize=96",
//						"https://www.newegg.com/p/pl?d=RTX%203070&N=8000%204814%20100006662&PageSize=96",
//						"https://www.newegg.com/p/pl?d=RTX%203070&N=4814%20100006662%208000&PageSize=96"
//				}),
//				new NeweggSearch("Nvidia 3060ti - Newegg USA", DiscordChannel.NVIDIA_3060TI_USA, MIN_WAIT_TIME, MAX_WAIT_TIME, new String[]{
//						"https://www.newegg.com/p/pl?d=RTX%203060%20Ti&N=8000%208000%20100006662&PageSize=96",
//						"https://www.newegg.com/p/pl?d=RTX%203060%20Ti&N=8000%20100006662%204814"
//				}),
//
//				new NeweggSearch("AMD 6900XT - Newegg USA", DiscordChannel.AMD_RX6900XT_USA, MIN_WAIT_TIME, MAX_WAIT_TIME, new String[]{
//						"https://www.newegg.com/p/pl?d=6900xt&N=8000%20100006662&PageSize=96"
//				}),
//				new NeweggSearch("AMD 6800XT - Newegg USA", DiscordChannel.AMD_RX6800XT_USA, MIN_WAIT_TIME, MAX_WAIT_TIME, new String[]{
//						"https://www.newegg.com/p/pl?d=6800xt&N=8000%20100006662&PageSize=96"
//				}),
//				new NeweggSearch("AMD 6800 - Newegg USA", DiscordChannel.AMD_RX6800_USA, MIN_WAIT_TIME, MAX_WAIT_TIME, new String[]{
//						"https://www.newegg.com/p/pl?d=rx6800%20-xt&N=8000%20100006662&PageSize=96"
//				}),
//
//				new NeweggSearch("AMD 5950x - Newegg USA", DiscordChannel.AMD_5950x_USA, MIN_WAIT_TIME, MAX_WAIT_TIME, new String[]{
//						"https://www.newegg.com/p/pl?d=100-100000059WOF&N=100006676%204814%208000&PageSize=96"
//				}),
//				new NeweggSearch("AMD 5900x - Newegg USA", DiscordChannel.AMD_5900x_USA, MIN_WAIT_TIME, MAX_WAIT_TIME, new String[]{
//						"https://www.newegg.com/p/pl?d=100-100000061WOF&N=100006676%204814%208000&PageSize=96"
//				}),
//				new NeweggSearch("AMD 5800x - Newegg USA", DiscordChannel.AMD_5800x_USA, MIN_WAIT_TIME, MAX_WAIT_TIME, new String[]{
//						"https://www.newegg.com/p/pl?d=100-100000063WOF&N=100006676%204814%208000&PageSize=96",
//						"https://www.newegg.com/p/pl?d=100-100000063WOF&N=8000%20100006676%204814",
//						"https://www.newegg.com/p/pl?d=100-100000063WOF&N=100006676%208000%204814"
//				}),
//				new NeweggSearch("AMD 5600X - Newegg USA", DiscordChannel.AMD_5600x_USA, MIN_WAIT_TIME, MAX_WAIT_TIME, new String[]{
//						"https://www.newegg.com/p/pl?d=100-100000065BOX&N=100006676%204814%208000&PageSize=96",
//						"https://www.newegg.com/p/pl?d=100-100000065BOX&N=8000%20100006676%204814",
//						"https://www.newegg.com/p/pl?d=100-100000065BOX&N=100006676%208000%204814"
//				}),
//
//				//
//				new BestBuySearch("Nvidia 3090 - BestBuy USA", DiscordChannel.NVIDIA_3090_USA, MIN_WAIT_TIME, MAX_WAIT_TIME, new String[]{
//						"https://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&id=pcat17071&iht=y&keys=keys&ks=960&list=n&qp=category_facet%3DGPUs%20%2F%20Video%20Graphics%20Cards~abcat0507002&sc=Global&st=rtx%203090&type=page&usc=All%20Categories"
//				}),
//				new BestBuySearch("Nvidia 3080 - BestBuy USA", DiscordChannel.NVIDIA_3080_USA, MIN_WAIT_TIME, MAX_WAIT_TIME, new String[]{
//						"https://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&id=pcat17071&iht=y&keys=keys&ks=960&list=n&qp=category_facet%3DGPUs%20%2F%20Video%20Graphics%20Cards~abcat0507002&sc=Global&st=rtx%203080&type=page&usc=All%20Categories"
//				}),
//				new BestBuySearch("Nvidia 3070 - BestBuy USA", DiscordChannel.NVIDIA_3070_USA, MIN_WAIT_TIME, MAX_WAIT_TIME, new String[]{
//						"https://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&id=pcat17071&iht=y&keys=keys&ks=960&list=n&qp=category_facet%3DGPUs%20%2F%20Video%20Graphics%20Cards~abcat0507002&sc=Global&st=rtx%203070&type=page&usc=All%20Categories"
//				}),
				new BestBuySearch("Nvidia 3060ti - BestBuy USA", DiscordChannel.ADMIN_ERRORS, MIN_WAIT_TIME, MAX_WAIT_TIME, new String[]{
						"https://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&id=pcat17071&iht=y&keys=keys&ks=960&list=n&qp=category_facet%3DGPUs%20%2F%20Video%20Graphics%20Cards~abcat0507002&sc=Global&st=rtx%203060%20ti&type=page&usc=All%20Categories"
				}),
//				new BestBuySearch("AMD 6900XT - BestBuy USA", DiscordChannel.AMD_RX6900XT_USA, MIN_WAIT_TIME, MAX_WAIT_TIME, new String[]{
//						"https://www.bestbuy.com/site/searchpage.jsp?st=amd+6900+gpu&_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=&sp=&qp=&list=n&af=true&iht=y&usc=All+Categories&ks=960&keys=keys"
//				}),
//
//				new BestBuySearch("AMD 6800XT - BestBuy USA", DiscordChannel.AMD_RX6800XT_USA, MIN_WAIT_TIME, MAX_WAIT_TIME, new String[]{
//						"https://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&id=pcat17071&iht=y&keys=keys&ks=960&list=n&qp=category_facet%3DGPUs%20%2F%20Video%20Graphics%20Cards~abcat0507002%5Egpusv_facet%3DGraphics%20Processing%20Unit%20(GPU)~AMD%20Radeon%20RX%206800%20XT&sc=Global&st=amd%206800&type=page&usc=All%20Categories"
//				}),
//				new BestBuySearch("AMD 6800 - BestBuy USA", DiscordChannel.AMD_RX6800_USA, MIN_WAIT_TIME, MAX_WAIT_TIME, new String[]{
//						"https://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&id=pcat17071&iht=y&keys=keys&ks=960&list=n&qp=category_facet%3DGPUs%20%2F%20Video%20Graphics%20Cards~abcat0507002%5Egpusv_facet%3DGraphics%20Processing%20Unit%20(GPU)~AMD%20Radeon%20RX%206800&sc=Global&st=amd%206800&type=page&usc=All%20Categories"
//				}),
//
//				new BestBuySearch("AMD 5950X - BestBuy USA", DiscordChannel.AMD_5950x_USA, MIN_WAIT_TIME, MAX_WAIT_TIME, new String[]{
//						"https://www.bestbuy.com/site/searchpage.jsp?st=5950x&_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=&sp=&qp=&list=n&af=true&iht=y&usc=All+Categories&ks=960&keys=keys"
//				}),
//				new BestBuySearch("AMD 5900X - BestBuy USA", DiscordChannel.AMD_5900x_USA, MIN_WAIT_TIME, MAX_WAIT_TIME, new String[]{
//						"https://www.bestbuy.com/site/searchpage.jsp?st=5900x&_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=&sp=&qp=&list=n&af=true&iht=y&usc=All+Categories&ks=960&keys=keys"
//				}),
//				new BestBuySearch("AMD 5800X - BestBuy USA", DiscordChannel.AMD_5800x_USA, MIN_WAIT_TIME, MAX_WAIT_TIME, new String[]{
//						"https://www.bestbuy.com/site/searchpage.jsp?st=5800x&_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=&sp=&qp=&list=n&af=true&iht=y&usc=All+Categories&ks=960&keys=keys"
//				}),
//				new BestBuySearch("AMD 5600X - BestBuy USA", DiscordChannel.AMD_5600x_USA, MIN_WAIT_TIME, MAX_WAIT_TIME, new String[]{
//						"https://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&id=pcat17071&iht=y&keys=keys&ks=960&list=n&qp=category_facet%3DCPUs%20%2F%20Processors~abcat0507010&sc=Global&st=5600x&type=page&usc=All%20Categories"
//				}),
		};

		System.out.println("Creating thread group...");
		final ThreadGroup allThreads = new ThreadGroup("Main groupy group thing");
		System.out.println("Adding all threads to thread group...");
		allThreads.enumerate(itemSearchList);

		for (final Thread curThread : itemSearchList) {
			System.out.println("Starting thread: " + curThread.getName());
			curThread.setDaemon(false);
			curThread.start();
		}

		System.out.println("Finished thread creation, time to stay looping!");

		while (true) {

//			System.out.println("activeCount == " + allThreads.activeCount());
//			System.out.println("activeCount == " + itemSearchList.length);

			if (allThreads.activeCount() < itemSearchList.length) {

			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}