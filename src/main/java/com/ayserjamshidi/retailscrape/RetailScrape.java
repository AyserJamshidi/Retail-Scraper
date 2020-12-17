package com.ayserjamshidi.retailscrape;

import com.ayserjamshidi.retailscrape.addons.discord.DiscordChannel;
import com.ayserjamshidi.retailscrape.searchresults.NeweggSearch;

public class RetailScrape {

	final static int MIN_WAIT_TIME = 5, MAX_WAIT_TIME = 15;

	public static void main(String[] args) {
		// Disable warnings at beginning
		java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
		java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(java.util.logging.Level.OFF); // Prevents super-spam when hard refreshing
		java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);

		String[] testy = new String[]{"", ""};

		System.out.println("Creating threads...");
		NeweggSearch[] itemSearchList = {
				// Nvidia GPUs
				new NeweggSearch("Nvidia 3090 - Newegg USA", DiscordChannel.NVIDIA_3090_USA, MIN_WAIT_TIME, MAX_WAIT_TIME,
						new String[]{
								"https://www.newegg.com/p/pl?d=RTX%203090&N=4814%208000%20100006662&PageSize=96",
//								"https://www.newegg.com/p/pl?d=RTX%203090&N=4814%20100006662%208000"
						}),
				new NeweggSearch("Nvidia 3080 - Newegg USA", DiscordChannel.NVIDIA_3080_USA, MIN_WAIT_TIME, MAX_WAIT_TIME,
						new String[]{
								"https://www.newegg.com/p/pl?d=RTX%203080&N=8000%208000%20100006662%204021%204022&PageSize=96",
//								"https://www.newegg.com/p/pl?d=RTX%203080&N=8000%208000%20100006662&PageSize=96",
//								"https://www.newegg.com/p/pl?d=RTX%203080&N=4022%20100006662%204021%204814%208000%204841",
//								"https://www.newegg.com/p/pl?d=RTX%203080&N=100006662%208000%204021%204841%204022%204814"
						}),
				new NeweggSearch("Nvidia 3070 - Newegg USA", DiscordChannel.NVIDIA_3070_USA, MIN_WAIT_TIME, MAX_WAIT_TIME,
						new String[]{
								"https://www.newegg.com/p/pl?d=RTX%203070&N=8000%208000%20100006662&PageSize=96",
//								"https://www.newegg.com/p/pl?d=RTX%203070&N=8000%204814%20100006662",
//								"https://www.newegg.com/p/pl?d=RTX%203070&N=4814%20100006662%208000"
						}),
				new NeweggSearch("Nvidia 3060ti - Newegg USA", DiscordChannel.NVIDIA_3060TI_USA, MIN_WAIT_TIME, MAX_WAIT_TIME,
						new String[]{
								"https://www.newegg.com/p/pl?d=RTX%203060%20Ti&N=8000%208000%20100006662&PageSize=96",
//								"https://www.newegg.com/p/pl?d=RTX%203060%20Ti&N=8000%20100006662%204814",
//								"https://www.newegg.com/p/pl?d=rtx+3060+ti?"
						}),

				// AMD GPUs
				new NeweggSearch("AMD 6900xt - Newegg USA", DiscordChannel.AMD_RX6900XT_USA, MIN_WAIT_TIME, MAX_WAIT_TIME,
						new String[]{
								"https://www.newegg.com/p/pl?d=6900xt&N=8000%20100006662&PageSize=96",
						}),
				new NeweggSearch("AMD 6800xt - Newegg USA", DiscordChannel.AMD_RX6800XT_USA, MIN_WAIT_TIME, MAX_WAIT_TIME,
						new String[]{
								"https://www.newegg.com/p/pl?d=6800xt&N=8000%20100006662&PageSize=96",
						}),
				new NeweggSearch("AMD 6800 - Newegg USA", DiscordChannel.AMD_RX6800_USA, MIN_WAIT_TIME, MAX_WAIT_TIME,
						new String[]{
								"https://www.newegg.com/p/pl?d=rx6800%20-xt&N=8000%20100006662&PageSize=96",
						}),

				// AMD CPUs
				new NeweggSearch("AMD 5950x - Newegg USA", DiscordChannel.AMD_5950x_USA, MIN_WAIT_TIME, MAX_WAIT_TIME,
						new String[]{
								"https://www.newegg.com/p/pl?d=100-100000059WOF&N=100006676%204814%208000&PageSize=96"
						}),
				new NeweggSearch("AMD 5900x - Newegg USA", DiscordChannel.AMD_5900x_USA, MIN_WAIT_TIME, MAX_WAIT_TIME,
						new String[]{
								"https://www.newegg.com/p/pl?d=100-100000061WOF&N=100006676%204814%208000&PageSize=96"
						}),
				new NeweggSearch("AMD 5800x - Newegg USA", DiscordChannel.AMD_5800x_USA, MIN_WAIT_TIME, MAX_WAIT_TIME,
						new String[]{
								"https://www.newegg.com/p/pl?d=100-100000063WOF&N=100006676%204814%208000&PageSize=96",
//								"https://www.newegg.com/p/pl?d=100-100000063WOF&N=8000%20100006676%204814",
//								"https://www.newegg.com/p/pl?d=100-100000063WOF&N=100006676%208000%204814"
						}),
				new NeweggSearch("AMD 5600x - Newegg USA", DiscordChannel.AMD_5600x_USA, MIN_WAIT_TIME, MAX_WAIT_TIME,
						new String[]{
								"https://www.newegg.com/p/pl?d=100-100000065BOX&N=100006676%204814%208000&PageSize=96",
//								"https://www.newegg.com/p/pl?d=100-100000065BOX&N=8000%20100006676%204814",
//								"https://www.newegg.com/p/pl?d=100-100000065BOX&N=100006676%208000%204814"
						}),
		};

		System.out.println("Creating thread group...");
		ThreadGroup allThreads = new ThreadGroup("Main groupy group thing");
		System.out.println("Adding all threads to thread group...");
		allThreads.enumerate(itemSearchList); // Add all child threads to thread group

		for (Thread curThread : itemSearchList) {
			System.out.println("Starting thread: " + curThread.getName());
			curThread.setDaemon(false); // Set as false to avoid it exiting with the main thread
			curThread.start();
		}

//		while (allThreads.activeCount() > 0)
//			if (allThreads.activeCount() < itemSearchList.length) {
//				for (Thread curThread : itemSearchList)
//					if (!curThread.isAlive())
//						new DiscordSenderTemplate().error("Thread " + curThread.getName() + " is not alive! Restart the program!");
//			} else
//				Thread.sleep(20000);

		System.out.println("Finished all main thread tasks!");
	}
}