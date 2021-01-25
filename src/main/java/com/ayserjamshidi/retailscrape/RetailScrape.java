package com.ayserjamshidi.retailscrape;

import com.ayserjamshidi.retailscrape.ThreadList.GeneralTrackingList;
import com.ayserjamshidi.retailscrape.addons.discord.DiscordAnnounce;
import com.ayserjamshidi.retailscrape.addons.proxything.AcquireProxyList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RetailScrape {

	public static List<String> proxyList = new ArrayList<>();
	public static int proxyIndex = 0;

	public static void main(final String[] args) {
		System.out.print("Setting global proxy list...  ");
		proxyList.addAll(new AcquireProxyList().retrieveProxyList());
		Collections.shuffle(proxyList);

		System.out.println("Retrieved " + proxyList.size() + " proxies");

		System.out.println("Creating threads...");

		final List<Thread> itemSearchList = new ArrayList<>();

		if (args.length > 0) {
			GeneralTrackingList trackingList = new GeneralTrackingList();

			for (String arg : args)
				switch (arg.toLowerCase()) {
					case "-newegg":
						Collections.addAll(itemSearchList, trackingList.getNewegg());
						break;
					case "-bestbuy":
						Collections.addAll(itemSearchList, trackingList.getBestBuy());
						break;
					case "-amazon":
//						Collections.addAll(itemSearchList, );
						break;
					case "-test":
						Collections.addAll(itemSearchList, trackingList.getNewegg());

//						Collections.addAll(itemSearchList, trackingList.getNewegg());

//						Collections.addAll(itemSearchList, new GeneralTrackingList().getTesting());
//						System.out.println("TEST MODE!!!  NO PARAMETERS FOUND!");
////						Collections.addAll(itemSearchList, trackingList.getTesting());
//						HTMLUnitTest thingy = new HTMLUnitTest("Tester Thread",
//								DiscordChannel.ADMIN_ERRORS, 2, 4,
//						new String[] {"https://www.newegg.com/p/pl?d=3080+gpu"}, "//div[@class='item-cell']");
//						thingy.init();
//						thingy.start();
						break;
				}
		}

		System.out.println("Creating thread group...");
		final ThreadGroup allThreads = new ThreadGroup("Main groupy group thing");
		System.out.println("Adding all threads to thread group...");
		allThreads.enumerate(itemSearchList.toArray(Thread[]::new));

		for (final Thread curThread : itemSearchList) {
			System.out.println("Starting thread: " + curThread.getName());
			curThread.setDaemon(false);
			curThread.start();

			try {
				Thread.sleep(500);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		System.out.println("Finished thread creation, time to stay looping!");

		// Wait for everything to start before scanning
		while (allThreads.activeCount() != itemSearchList.size()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Start infinitely scanning
		while (true) {
			if (allThreads.activeCount() < itemSearchList.size())
				DiscordAnnounce.error("A thread has died! Please restart the program ASAP!");

			try {
				Thread.sleep(60000 * 5); // Every 5 minutes
				System.out.println("Refreshing proxy list...");
				proxyList = new AcquireProxyList().retrieveProxyList();
				System.out.println("Refreshed!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}