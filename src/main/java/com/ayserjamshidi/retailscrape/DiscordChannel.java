package com.ayserjamshidi.retailscrape;

public enum DiscordChannel {
	MAIN_CHANNEL("https://discord.com/api/webhooks/774850994286624768/-4obrF_fFMdKvsOfJNLMyPa8e2bCa1ggw3X2Rt_QCNhYwhaIl6JBTTYS1Ho22-u5Sl_l"),
	TEST_CHANNEL_WEBHOOK("https://discord.com/api/webhooks/780002736581378088/w9doWBCicgG6Bgj_tsfevNym4QwMcJQ9Kl8g85JA0yuPxGwrCh-mR9d2Po0oek0xjajc");

	public final String webhookUrl;

	DiscordChannel(String webhookUrl) {
		this.webhookUrl = webhookUrl;
	}
}