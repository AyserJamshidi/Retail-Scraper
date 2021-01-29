package com.ayserjamshidi.retailscrape.threads;

import com.ayserjamshidi.retailscrape.addons.discord.DiscordAnnounce;

public class DeadThreadChecker extends Thread {

	private final ThreadGroup allThreads;
	private final int itemSize;

	public DeadThreadChecker(ThreadGroup allThreads, int itemSize) {
		this.allThreads = allThreads;
		this.itemSize = itemSize;
	}

	@Override
	public void run() {
		while (!this.isInterrupted()) {
			try {
				if (allThreads.activeCount() < itemSize)
					DiscordAnnounce.error("A thread has died! Please restart the program ASAP!");

				Thread.sleep(2000);
			} catch (InterruptedException e) {
				DiscordAnnounce.error("An error occurred while attempting to send a message to discord");
				e.printStackTrace();
			}
		}
	}
}