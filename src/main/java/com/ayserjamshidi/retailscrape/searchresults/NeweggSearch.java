package com.ayserjamshidi.retailscrape.searchresults;

import com.ayserjamshidi.retailscrape.addons.discord.DiscordAnnounce;
import org.openqa.selenium.WebElement;
import com.ayserjamshidi.retailscrape.searchresults.searcheditem.NeweggSearchItem;
import org.openqa.selenium.By;
import com.ayserjamshidi.retailscrape.addons.discord.DiscordChannel;

public class NeweggSearch extends WebSearch {
	final int GENERAL_WAIT_TIME_MINUTES = 10;

	public NeweggSearch(final String threadName, final DiscordChannel discordChannel, final int MIN_WAIT_SECONDS, final int MAX_WAIT_SECONDS, final String[] pageUrl) {
		super(threadName, discordChannel, MIN_WAIT_SECONDS, MAX_WAIT_SECONDS, pageUrl, By.className("item-cell"));
	}

	@Override
	protected boolean itemIsCombo(final Object obj) {
		return ((NeweggSearchItem) obj).itemUrl.contains("ComboDealDetails");
	}

	@Override
	protected boolean itemIsGood(final WebElement cell) {
		return this.getFirstElement(cell, By.className("txt-ads-box")) == null && this.getFirstElement(cell, By.className("item-sponsored")) == null;
	}

	@Override
	protected boolean pageReloaded() {
		return this.driver.getPageSource().toLowerCase().contains("shipped by newegg");
	}

	@Override
	protected void badPageReload() {
		final String title = this.driver.getTitle().toLowerCase();
		if (title.contains("are you a human?")) {
			new DiscordAnnounce().error("[" + this.getName() + "] - IP has been flagged.  Change VPN source!");
			System.out.println("IP has been flagged.");
		} else if (title.contains("403 error")) {
			System.out.println("IP has been banned.");
			new DiscordAnnounce().error("[" + this.getName() + "] - IP has been banned.  Change VPN source!");
		} else {
			new DiscordAnnounce().error("[" + this.getName() + "] - Something happened while trying to reload URL.");
		}
	}
}