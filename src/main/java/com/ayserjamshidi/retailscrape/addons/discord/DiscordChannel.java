package com.ayserjamshidi.retailscrape.addons.discord;

import java.awt.*;

public enum DiscordChannel {
	COMBO_USA("https://discord.com/api/webhooks/784184484986880031/4x1Y-WefyrJsvSjGkmjNHdch9k4NcK6D1ddCr01qudZcSCCZ3ZzOOezb5EqWuo85VjIP",
			null, null),

	// Nvidia GPU
	NVIDIA_3090_USA("https://discord.com/api/webhooks/784184484986880031/4x1Y-WefyrJsvSjGkmjNHdch9k4NcK6D1ddCr01qudZcSCCZ3ZzOOezb5EqWuo85VjIP",
			Color.GREEN, "<@&783878037720268802>"),
	NVIDIA_3080_USA("https://discord.com/api/webhooks/784190349870497813/usoYMQpsTvpRGBrez211zQnyAeoKHQx0o4sU5nM58nzRjOv_XgFOHCG-aoMXuWnCPoe7",
			Color.GREEN, "<@&783877959844364309>"),
	NVIDIA_3070_USA("https://discord.com/api/webhooks/784190523581661224/91B3f12oj-m0ukU2tvT8G8LDRgu0Mlcc7ulwTaMKr0Gtt4598iSrUqr5rOX5XnQOw6GB",
			Color.GREEN, "<@&782325238490333224>"),
	NVIDIA_3060TI_USA("https://discord.com/api/webhooks/784190595005939722/aMkoQrznr2VrPaUVp0WtB3yhJyuCYnlvW0gzkSUaec13l88HIvuqZqsfMVSyFZX3hmBs",
			Color.GREEN, "<@&783869289005842494>"),

	// AMD GPU
	AMD_RX6900XT_USA("https://discord.com/api/webhooks/784355182145306644/Pn8mckmwO7gwZ6pQxTZnxZo6Ha_dmAxtISTzflbFQFx9KEUCS8oo9pbW2vc1d-yKvP4x",
			Color.RED, "<@&784345546101489664>"),
	AMD_RX6800XT_USA("https://discord.com/api/webhooks/784355401344483328/WM-FWIirb4oXiHSk0kg559D8VkN01_JIJYTBWk2byHCwvqDZSW7mL5oAndpFDseHAWkV",
			Color.RED, "<@&784345445974409237>"),
	AMD_RX6800_USA("https://discord.com/api/webhooks/784355485137502208/Ux7s9ULDgdPYQj0DdiyiePZErphMLpApcf4FEcgmPG2Bpbgs1rrdCMXMHmu6cqURH02T",
			Color.RED, "<@&784345247714377748>"),

	// AMD CPU
	AMD_5950x_USA("https://discord.com/api/webhooks/784214130915278859/MEkV64dR7DfW1sNCeLoAO5katR9v0HF4ajvK2Ji88eUxHsIuLeLqjBr-6papOYiZl9BV",
			Color.RED, "<@&783881400985059359>"),
	AMD_5900x_USA("https://discord.com/api/webhooks/784214410084352020/NSHOrsZzihGrE_NfrEXWCt9ZcGWL2E9eg9xTrJXr2JvJyXMLs_5nrOkblm2-uZr3GwQR",
			Color.RED, "<@&783881406345379851>"),
	AMD_5800x_USA("https://discord.com/api/webhooks/784214523279966228/DJ4s-3Lc6WAzHOMiL70dmcjbLqe3sTHtLejZTvnO8_FjZOK8fK_8gYmtsmyUY7SN03Ap",
			Color.RED, "<@&783881406770053130>"),
	AMD_5600x_USA("https://discord.com/api/webhooks/784214589348511775/venqxkPVPOv-CfknMuCP5FLPN9n944ejcgCyEXzlufgf6e9pPN1dp4SOCIZ_o6aebY58",
			Color.RED, "<@&783881174140846130>"),

	// Other
	ADMIN_ERRORS("https://discord.com/api/webhooks/784586795572133918/kbQtDzm72ijonrsiDfgYrO7-NijwExDhYegHYvpiaXrB79UL5tJD31Y6UBcIpNAdGpc4",
			Color.RED, "<@&785243679862292481>"),
	;

	public final String channel, role;
	public final Color embedColor;

	DiscordChannel(String channel, Color embedColor, String role) {
		this.channel = channel;
		this.embedColor = embedColor;
		this.role = role;
	}
}