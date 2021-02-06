package com.ayserjamshidi.retailscrape;

import com.ayserjamshidi.retailscrape.ThreadList.GeneralTrackingList;
import com.ayserjamshidi.retailscrape.addons.proxything.AcquireProxyList;
import com.ayserjamshidi.retailscrape.threads.DeadThreadChecker;
import com.ayserjamshidi.retailscrape.threads.DiscordAnnouncer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RetailScrape {

	public static List<String> proxyList = new ArrayList<>();
	public static int proxyIndex = 0;

	public static void main(final String[] args) {
//		if (true) {
//			DiscordSenderTrash.send();
//			System.exit(0);
//		}
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
					case "-newegg_usa":
						Collections.addAll(itemSearchList, trackingList.getNeweggUSA());
						break;
					case "-bestbuy_usa":
						Collections.addAll(itemSearchList, trackingList.getBestBuyUSA());
						break;
					case "-amazon_usa":
						Collections.addAll(itemSearchList, trackingList.getAmazonUSA());
						break;
					case "-newegg_ca":
						Collections.addAll(itemSearchList, trackingList.getNeweggCA());
						break;
					case "-test":
						RetailConfig.TEST_MODE = true;
						RetailConfig.OPEN_IN_BROWSER_MODE = true;
						Collections.addAll(itemSearchList, trackingList.getAmazonUSA());
						Collections.addAll(itemSearchList, trackingList.getNeweggCA());
						Collections.addAll(itemSearchList, trackingList.getBestBuyUSA());
						Collections.addAll(itemSearchList, trackingList.getNeweggUSA());
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
		}

		System.out.println("Finished thread creation, time to stay looping!");

		// Wait for everything to start before scanning
		/*while (allThreads.activeCount() != itemSearchList.size()) {
			try {
				System.out.println("Still inside... activeCount == " + allThreads.activeCount() + " | size == " + itemSearchList.size());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/

		new DiscordAnnouncer().start();
		new DeadThreadChecker(allThreads, itemSearchList.size()).start();
	}
}