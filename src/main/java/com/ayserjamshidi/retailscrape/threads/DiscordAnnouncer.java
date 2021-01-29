package com.ayserjamshidi.retailscrape.threads;

import com.ayserjamshidi.retailscrape.addons.discord.DiscordChannel;
import com.ayserjamshidi.retailscrape.addons.discord.DiscordWebhook;

import java.util.List;

public class DiscordAnnouncer extends Thread {
	public static List<DiscordWebhook> webhookList;

	public DiscordAnnouncer() {
		this.setDaemon(false); // Allows this thread to run even after the main thread has closed.
	}

	@Override
	public void run() {
		System.out.println("[DiscordAnnouncer] - Started.");
		while (!this.isInterrupted()) {
			try {
				for (DiscordWebhook curWebhook : webhookList) {
					curWebhook.execute();
					webhookList.remove(curWebhook);
					sleep(334);
				}
				sleep(334);
			} catch (Exception ex) {
				error("Something happened while attempting to send a discord message!");
				ex.printStackTrace();
			}
		}
	}


	public static void error(final String errorMessage) {
		try {
			final DiscordWebhook webhook = new DiscordWebhook(DiscordChannel.ADMIN_ERRORS.channel);
			webhook.setUsername("Error");
			webhook.setContent(DiscordChannel.ADMIN_ERRORS.role + " - " + errorMessage);
			webhookList.add(webhook);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
