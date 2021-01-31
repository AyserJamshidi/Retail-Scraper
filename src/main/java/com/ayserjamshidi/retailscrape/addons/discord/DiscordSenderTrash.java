package com.ayserjamshidi.retailscrape.addons.discord;

import java.awt.*;

enum test {

	USA("https://ayser.tech/bot/images/usa.png",
			"<@&786385391397765120>",
			// Nvidia
			"<@&783878037720268802>",
			"<@&783877959844364309>",
			"<@&782325238490333224>",
			"<@&783869289005842494>",

			// AMD
			"<@&784345546101489664>",
			"<@&784345445974409237>",
			"<@&784345247714377748>",

			"<@&783881400985059359>",
			"<@&783881406345379851>",
			"<@&783881406770053130>",
			"<@&783881174140846130>"),

	CANADA("https://ayser.tech/bot/images/canada.png",
			"<@&804910191933522011>",
			// Nvidia
			"<@&804426066688213002>",
			"<@&804427055345172591>",
			"<@&804427055429058670>",
			"<@&804427218146295819>",

			// AMD
			"<@&804427250518196285>",
			"<@&804427255807344731>",
			"<@&804427275658723348>",

			"<@&804427230985846795>",
			"<@&804427235263119401>",
			"<@&804427240129167401>",
			"<@&804427246495989809>"),
	;

	public final String flagUrl, combo, rtx3090, rtx3080, rtx3070, rtx3060ti, rx6900xt, rx6800xt,
			rx6800, amd5950x, amd5900x, amd5800x, amd5600x;

	test(String flagUrl, String combo, String rtx3090, String rtx3080, String rtx3070, String rtx3060ti, String rx6900xt,
	     String rx6800xt, String rx6800, String amd5950x, String amd5900x, String amd5800x, String amd5600x) {
		this.flagUrl = flagUrl;
		this.combo = combo;

		this.rtx3090 = rtx3090;
		this.rtx3080 = rtx3080;
		this.rtx3070 = rtx3070;
		this.rtx3060ti = rtx3060ti;

		this.rx6900xt = rx6900xt;
		this.rx6800xt = rx6800xt;
		this.rx6800 = rx6800;

		this.amd5950x = amd5950x;
		this.amd5900x = amd5900x;
		this.amd5800x = amd5800x;
		this.amd5600x = amd5600x;
	}
}

public class DiscordSenderTrash {
	public static void send() {
		try {
			for (test curRegion : test.values()) {
				DiscordWebhook outputWebhook = new DiscordWebhook(DiscordChannel.COMMAND_SPAM.channel);
//				DiscordWebhook outputWebhook = new DiscordWebhook(DiscordChannel.NOTIFICATION_SIGNUP.channel);
				final DiscordWebhook.EmbedObject messageObject = new DiscordWebhook.EmbedObject();

				outputWebhook.setUsername("Ace");
				outputWebhook.setAvatarUrl("https://i.imgur.com/wTwIARf.png");

				messageObject.setTitle("━━━━━━━━━━━━━━━━━━━━━━━━");
				messageObject.setThumbnail(curRegion.flagUrl);
				messageObject.setColor(Color.CYAN);

				messageObject.addField("━━━━━━ **GPU** ━━━━━━",
						"<:rtx3090:784516389016567869> :\u200Ertx3090: for " + curRegion.rtx3090 + "\\n" +
								"<:rtx3080:784516397416841278> :\u200Ertx3080: for " + curRegion.rtx3080 + "\\n" +
								"<:rtx3070:784516403200786452> :\u200Ertx3070: for " + curRegion.rtx3070 + "\\n" +
								"<:rtx3060ti:784516408339726346> :\u200Ertx3060ti: for " + curRegion.rtx3060ti + "\\n" +
								"\\n" +
								":money_with_wings: :\u200Emoney_with_wings: for " + curRegion.rx6900xt + "\\n" +
								":moneybag: :\u200Emoneybag: for " + curRegion.rx6800xt + "\\n" +
								":money_mouth: :\u200Emoney_mouth: for " + curRegion.rx6800 + "\\n" +
								"\\n" +
								"━━━━━━ **CPU** ━━━━━━\\n" +
								"<:5950x:784557264173662208> :\u200E5950x: for " + curRegion.amd5950x + "\\n" +
								"<:5900x:784557235480821801> :\u200E5900x: for " + curRegion.amd5900x + "\\n" +
								"<:5800x:784557211476164648> :\u200E5800x: for " + curRegion.amd5800x + "\\n" +
								"<:5600x:784557181944725555> :\u200E5600x: for " + curRegion.amd5600x + "\\n" +
								"\\n" +
								"━━━━━━ **Other** ━━━━━━\\n" +
								":x: :\u200Ex: for " + curRegion.combo, true);

				outputWebhook.addEmbed(messageObject);

				outputWebhook.execute();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}