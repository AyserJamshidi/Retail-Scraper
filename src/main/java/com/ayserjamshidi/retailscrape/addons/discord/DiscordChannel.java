package com.ayserjamshidi.retailscrape.addons.discord;

import java.awt.*;

public enum DiscordChannel {
	// Nvidia GPU
	NVIDIA_3090_USA("https://discord.com/api/webhooks/784184484986880031/4x1Y-WefyrJsvSjGkmjNHdch9k4NcK6D1ddCr01qudZcSCCZ3ZzOOezb5EqWuo85VjIP",
			"https://discord.com/api/webhooks/787797365271560193/n1pr63SS18BSNKdgsD8l4Xsda2FIRz74Rx9yqvoWqvOrWmoXhUbAAQDy6UkA5i9bYd_r",
			Color.GREEN,
			"<@&783878037720268802>"),
	NVIDIA_3080_USA("https://discord.com/api/webhooks/784190349870497813/usoYMQpsTvpRGBrez211zQnyAeoKHQx0o4sU5nM58nzRjOv_XgFOHCG-aoMXuWnCPoe7",
			"https://discord.com/api/webhooks/787798138744340492/sk0Zv4cVu93RT2YBb2jkfGX1YJqh5SXKUBeiiZelTUQLAXuMwuNR_YcKFng1XbZZktIm",
			Color.GREEN, "<@&783877959844364309>"),
	NVIDIA_3070_USA("https://discord.com/api/webhooks/784190523581661224/91B3f12oj-m0ukU2tvT8G8LDRgu0Mlcc7ulwTaMKr0Gtt4598iSrUqr5rOX5XnQOw6GB",
			"https://discord.com/api/webhooks/787798267726397440/uUeNNkfgWcCuHdJn_m__6NwvU3JfKylaQmEu835F-cDTUCkT4sdwFbupsKWM8wi_zTdo",
			Color.GREEN, "<@&782325238490333224>"),
	NVIDIA_3060TI_USA("https://discord.com/api/webhooks/784190595005939722/aMkoQrznr2VrPaUVp0WtB3yhJyuCYnlvW0gzkSUaec13l88HIvuqZqsfMVSyFZX3hmBs",
			"https://discord.com/api/webhooks/787798416666525716/Z7PjpxWs5qepe5wEGKDkOHBrIJyL_BKkDB8QA1YrfALr3_A84VfvhYTrSQgPkCiMDvRT",
			Color.GREEN, "<@&783869289005842494>"),

	// AMD GPU
	AMD_RX6900XT_USA("https://discord.com/api/webhooks/784355182145306644/Pn8mckmwO7gwZ6pQxTZnxZo6Ha_dmAxtISTzflbFQFx9KEUCS8oo9pbW2vc1d-yKvP4x",
			"https://discord.com/api/webhooks/787800227221340201/mEbzyam7cYWeYvVB7SoQL5Bk5M_ceYv4ACkVmtNXUypPSyfG6GcIPRzwcbgoEKZK2oEF",
			Color.RED, "<@&784345546101489664>"),
	AMD_RX6800XT_USA("https://discord.com/api/webhooks/784355401344483328/WM-FWIirb4oXiHSk0kg559D8VkN01_JIJYTBWk2byHCwvqDZSW7mL5oAndpFDseHAWkV",
			"https://discord.com/api/webhooks/787800430926233622/65CHMOydm-xChOwtamsqi9K9k_a542dHpPb_xRrruxTAUZllr7zooGxDGkXChoXxOwdJ",
			Color.RED, "<@&784345445974409237>"),
	AMD_RX6800_USA("https://discord.com/api/webhooks/784355485137502208/Ux7s9ULDgdPYQj0DdiyiePZErphMLpApcf4FEcgmPG2Bpbgs1rrdCMXMHmu6cqURH02T",
			"https://discord.com/api/webhooks/787800510308679682/X9Gp9xeancqn57x7h5xU2Zg24ZOUL1ilu1_LJp8akiPGjO46GDKOXk4OXznrOBtfGwjR",
			Color.RED, "<@&784345247714377748>"),

	// AMD CPU
	AMD_5950x_USA("https://discord.com/api/webhooks/784214130915278859/MEkV64dR7DfW1sNCeLoAO5katR9v0HF4ajvK2Ji88eUxHsIuLeLqjBr-6papOYiZl9BV",
			"https://discord.com/api/webhooks/787800572702752791/xE1ElHvR1VMaHrXAyDdNkBPp_KK_dk-w_p1Gz2lqtQEsIJk8uLHiZwe0agv0q-U-l5NV",
			Color.RED, "<@&783881400985059359>"),
	AMD_5900x_USA(
			"https://discord.com/api/webhooks/784214410084352020/NSHOrsZzihGrE_NfrEXWCt9ZcGWL2E9eg9xTrJXr2JvJyXMLs_5nrOkblm2-uZr3GwQR",
			"https://discord.com/api/webhooks/787800626222465024/ZvVe-xBwHm0cYRTUK3QFcKmfrWcMggvNgMv5rPsknJsxtEHCXi3Ncl0axKRkc63IWC_a",
			Color.RED, "<@&783881406345379851>"),
	AMD_5800x_USA(
			"https://discord.com/api/webhooks/784214523279966228/DJ4s-3Lc6WAzHOMiL70dmcjbLqe3sTHtLejZTvnO8_FjZOK8fK_8gYmtsmyUY7SN03Ap",
			"https://discord.com/api/webhooks/787800659751469106/JCFVZ_VIhv1mgr29tg0jAV84FXzScnTtFbHyPhfYY1e85XjbTj4g1ZbEGEcI1t0YWlm0",
			Color.RED, "<@&783881406770053130>"),
	AMD_5600x_USA("https://discord.com/api/webhooks/784214589348511775/venqxkPVPOv-CfknMuCP5FLPN9n944ejcgCyEXzlufgf6e9pPN1dp4SOCIZ_o6aebY58",
			"https://discord.com/api/webhooks/787800688612212786/cyO1aXGJgDT8zo0DT3f_9oR3f6M9SjUSFRHqp041iLq1tcdW-jDeOoLPD8e1zbg0Y5rN",
			Color.RED, "<@&783881174140846130>"),

	// Other
	ADMIN_ERRORS("https://discord.com/api/webhooks/784586795572133918/kbQtDzm72ijonrsiDfgYrO7-NijwExDhYegHYvpiaXrB79UL5tJD31Y6UBcIpNAdGpc4",
			"https://discord.com/api/webhooks/784586795572133918/kbQtDzm72ijonrsiDfgYrO7-NijwExDhYegHYvpiaXrB79UL5tJD31Y6UBcIpNAdGpc4",
			Color.RED,
			"<@&785243679862292481>"),
	;

	public final String normalChannel, comboChannel, role;
	public final Color embedColor;

	DiscordChannel(String normalChannel, String comboChannel, Color embedColor, String role) {
		this.normalChannel = normalChannel;
		this.comboChannel = comboChannel;
		this.embedColor = embedColor;
		this.role = role;
	}
}