package com.ayserjamshidi.retailscrape;

import com.ayserjamshidi.retailscrape.addons.discord.DiscordChannel;
import com.ayserjamshidi.retailscrape.addons.discord.announcing.DiscordSenderTemplate;
import com.ayserjamshidi.retailscrape.searchresults.NeweggSearch;

import java.util.ArrayList;
import java.util.List;

public class RetailScrape {

	final static int MIN_WAIT_TIME = 10, MAX_WAIT_TIME = 15;
	public static int increaseMe = 0;

	public static void main(String[] args) throws InterruptedException {
		// Disable warnings at beginning
		java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
		java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);

		System.out.println("Creating Newegg search list...");
		NeweggSearch[] itemSearchList = {

				// Nvidia GPUs
				new NeweggSearch("Nvidia 3090 - Newegg USA", DiscordChannel.NVIDIA_3090_USA, MIN_WAIT_TIME, MAX_WAIT_TIME,
						"https://www.newegg.com/p/pl?d=RTX%203090&N=4814%208000%20100006662&PageSize=96"),
				new NeweggSearch("Nvidia 3080 - Newegg USA", DiscordChannel.NVIDIA_3080_USA, MIN_WAIT_TIME, MAX_WAIT_TIME,
						"https://www.newegg.com/p/pl?d=RTX%203080&N=8000%208000%20100006662&PageSize=96"),
				new NeweggSearch("Nvidia 3070 - Newegg USA", DiscordChannel.NVIDIA_3070_USA, MIN_WAIT_TIME, MAX_WAIT_TIME,
						"https://www.newegg.com/p/pl?d=RTX%203070&N=8000%208000%20100006662&PageSize=96"),
				new NeweggSearch("Nvidia 3060ti - Newegg USA", DiscordChannel.NVIDIA_3060TI_USA, MIN_WAIT_TIME, MAX_WAIT_TIME,
						"https://www.newegg.com/p/pl?d=RTX%203060%20Ti&N=8000%208000%20100006662&PageSize=96"),

				// AMD GPUs
				new NeweggSearch("AMD 6900xt - Newegg USA", DiscordChannel.AMD_RX6900XT_USA, MIN_WAIT_TIME, MAX_WAIT_TIME,
						"https://www.newegg.com/p/pl?d=6900xt&N=8000%20100006662&PageSize=96"),
				new NeweggSearch("AMD 6800xt - Newegg USA", DiscordChannel.AMD_RX6800XT_USA, MIN_WAIT_TIME, MAX_WAIT_TIME,
						"https://www.newegg.com/p/pl?d=6800xt&N=8000%20100006662&PageSize=96"),
				new NeweggSearch("AMD 6800 - Newegg USA", DiscordChannel.AMD_RX6800_USA, MIN_WAIT_TIME, MAX_WAIT_TIME,
						"https://www.newegg.com/p/pl?d=rx6800%20-xt&N=8000%20100006662&PageSize=96"),

				// AMD CPUs
				new NeweggSearch("AMD 5950x - Newegg USA", DiscordChannel.AMD_5950x_USA, MIN_WAIT_TIME, MAX_WAIT_TIME,
						"https://www.newegg.com/p/pl?d=100-100000059WOF&N=100006676%204814%208000&PageSize=96"),
				new NeweggSearch("AMD 5900x - Newegg USA", DiscordChannel.AMD_5900x_USA, MIN_WAIT_TIME, MAX_WAIT_TIME,
						"https://www.newegg.com/p/pl?d=100-100000061WOF&N=100006676%204814%208000&PageSize=96"),
				new NeweggSearch("AMD 5800x - Newegg USA", DiscordChannel.AMD_5800x_USA, MIN_WAIT_TIME, MAX_WAIT_TIME,
						"https://www.newegg.com/p/pl?d=100-100000063WOF&N=100006676%204814%208000&PageSize=96"),
				new NeweggSearch("AMD 5600x - Newegg USA", DiscordChannel.AMD_5600x_USA, MIN_WAIT_TIME, MAX_WAIT_TIME,
						"https://www.newegg.com/p/pl?d=100-100000065BOX&N=100006676%204814%208000&PageSize=96")
		};

		System.out.println("Initializing main loop...");
		List<Thread> threadList = new ArrayList<>();

		for (NeweggSearch curItem : itemSearchList) {
			Thread currentThread = new Thread(curItem);

			currentThread.start();
			threadList.add(currentThread);
			System.out.println("Started thread for " + curItem.threadTitle);
		}

		while (true) { // Infinite loop.  Will stop when all threads are closed.
			boolean keepLooping = false;

			for (Thread curThread : threadList)
				if (curThread.isAlive()) {
					keepLooping = true;
					break;
				} else {
					new DiscordSenderTemplate().error("Thread " + curThread.getName() + " is not alive! Restart the program!");
//					threadList.remove(curThread);
				}

			if (!keepLooping)
				break;

			//noinspection BusyWait
			Thread.sleep(2000);
		}

		System.out.println("All threads are not alive!");
	}
}