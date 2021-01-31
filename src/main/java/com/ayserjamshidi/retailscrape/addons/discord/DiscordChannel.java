package com.ayserjamshidi.retailscrape.addons.discord;

import java.awt.*;

public enum DiscordChannel {
	COMBO_USA("https://discord.com/api/webhooks/784184484986880031/4x1Y-WefyrJsvSjGkmjNHdch9k4NcK6D1ddCr01qudZcSCCZ3ZzOOezb5EqWuo85VjIP",
			null, null),
	COMBO_CA("https://discord.com/api/webhooks/804915321189957682/Xglit91ieSVmLlCWHXLYImox4up3VKcnxz-v8PuU4L4Z2-FNOK_qcdTn3K_YyGXBjhkv",
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
	NVIDIA_3090_CA("https://discord.com/api/webhooks/804915300902109224/3HgArfYQR8MYvQ_J0tjr8BunvkJ9ojUr2asoK1v4jCwWj_3Dg1cVt5VNYKDqyg6pi3iQ",
			Color.GREEN, "<@&804426066688213002>"),
	NVIDIA_3080_CA("https://discord.com/api/webhooks/804915351233888296/pw44LwZ-szEoB52tpjMVz-IeEbAtjuxNwi7-ZBsNe4NSFwNF00ksXZBCEeT1kzaRar0O",
			Color.GREEN, "<@&804427055345172591>"),
	NVIDIA_3070_CA("https://discord.com/api/webhooks/804915375765454849/BufGe0NjJF06sI5o9FDWA606EkBJ_DnSnLz6Nf50PuXE2gXwvXlIbh8n6D1zAseaf5Xj",
			Color.GREEN, "<@&804427055429058670>"),
	NVIDIA_3060TI_CA("https://discord.com/api/webhooks/804915399162068993/qK--TIG8BspBa5NiWI2qnQLZmdGwMnw1dpM-JxtK5ptoBLohH8lQgOR58fyvBcJsl5xy",
			Color.GREEN, "<@&804427218146295819>"),

	// AMD GPU
	AMD_RX6900XT_USA("https://discord.com/api/webhooks/784355182145306644/Pn8mckmwO7gwZ6pQxTZnxZo6Ha_dmAxtISTzflbFQFx9KEUCS8oo9pbW2vc1d-yKvP4x",
			Color.RED, "<@&784345546101489664>"),
	AMD_RX6800XT_USA("https://discord.com/api/webhooks/784355401344483328/WM-FWIirb4oXiHSk0kg559D8VkN01_JIJYTBWk2byHCwvqDZSW7mL5oAndpFDseHAWkV",
			Color.RED, "<@&784345445974409237>"),
	AMD_RX6800_USA("https://discord.com/api/webhooks/784355485137502208/Ux7s9ULDgdPYQj0DdiyiePZErphMLpApcf4FEcgmPG2Bpbgs1rrdCMXMHmu6cqURH02T",
			Color.RED, "<@&784345247714377748>"),
	AMD_RX6900XT_CA("https://discord.com/api/webhooks/804915429742739457/71OoMC2P8AcPtHHXVXatZ7_fLOSFAkhBtT6Q6O9xKhr1SHGBAD6fC2rZ7Spr5QKxEH5X",
			Color.RED, "<@&804427250518196285>"),
	AMD_RX6800XT_CA("https://discord.com/api/webhooks/804915453784358912/ANMOPL5ximyWXBE76W7GSmxeg36_VCv7vGcEXm9wDHrMWFspdW7dDHgzf32VfZu8AJwO",
			Color.RED, "<@&804427255807344731>"),
	AMD_RX6800_CA("https://discord.com/api/webhooks/804915492304846878/Fed_kK8UA0FGtpWZ_XNAFpg0QzJBp6c3ddGmCwbe735Mdx_LoPMuixqDisCl38UvrMCo",
			Color.RED, "<@&804427275658723348>"),

	// AMD CPU
	AMD_5950x_USA("https://discord.com/api/webhooks/784214130915278859/MEkV64dR7DfW1sNCeLoAO5katR9v0HF4ajvK2Ji88eUxHsIuLeLqjBr-6papOYiZl9BV",
			Color.RED, "<@&783881400985059359>"),
	AMD_5900x_USA("https://discord.com/api/webhooks/784214410084352020/NSHOrsZzihGrE_NfrEXWCt9ZcGWL2E9eg9xTrJXr2JvJyXMLs_5nrOkblm2-uZr3GwQR",
			Color.RED, "<@&783881406345379851>"),
	AMD_5800x_USA("https://discord.com/api/webhooks/784214523279966228/DJ4s-3Lc6WAzHOMiL70dmcjbLqe3sTHtLejZTvnO8_FjZOK8fK_8gYmtsmyUY7SN03Ap",
			Color.RED, "<@&783881406770053130>"),
	AMD_5600x_USA("https://discord.com/api/webhooks/784214589348511775/venqxkPVPOv-CfknMuCP5FLPN9n944ejcgCyEXzlufgf6e9pPN1dp4SOCIZ_o6aebY58",
			Color.RED, "<@&783881174140846130>"),
	AMD_5950x_CA("https://discord.com/api/webhooks/804915508637073459/9Rwfuu8plHA5Ilo8Acybfr-ZV902PzF1jpeeDVN2WsOkbaM0ckJiKYtlMdfnwF3wYMSm",
			Color.RED, "<@&804427230985846795>"),
	AMD_5900x_CA("https://discord.com/api/webhooks/804915532313657364/s-Bj-pP-SVR7oqUpHfeXytoIAeRZ3nXdLOxkDsJ0Mf8v0LJPpE8lRFxaiDz_kQIzkq-6",
			Color.RED, "<@&804427235263119401>"),
	AMD_5800x_CA("https://discord.com/api/webhooks/804915556557652010/v0OeCCrjKGeeXfXujeGeyMgqRgkvfHMlWNFmdAh88avF8VQcYzg1SJ_ewtsjJhFTTHPF",
			Color.RED, "<@&804427240129167401>"),
	AMD_5600x_CA("https://discord.com/api/webhooks/804915574231924747/JzIK6GWa4vS4i3rJqzzGdPugpeQMoiSq8jFfLXsvNcZmTpCekOcmzV9OsaN8TCVJqfuf",
			Color.RED, "<@&804427246495989809>"),

	// Other
	ADMIN_ERRORS("https://discord.com/api/webhooks/784586795572133918/kbQtDzm72ijonrsiDfgYrO7-NijwExDhYegHYvpiaXrB79UL5tJD31Y6UBcIpNAdGpc4",
			Color.RED, "<@&785243679862292481>"),
	COMMAND_SPAM("https://discord.com/api/webhooks/804975021196967946/HCB9OlsgJiP4avn9UwPcFtfBp61r0l1v2ucQcBu7BIYGSSJ_bj2CAgL75Th8FRQS_MY_",
			null, null),
	NOTIFICATION_SIGNUP("https://discord.com/api/webhooks/785097587556483103/ME2JkyrC1YW5EBkXGF7h32otIpMMM6va9pWmFxq_B2a9fxu9G8eWKEFVSZiMXT-VXWx6",
			null, null),
	;

	public final String channel, role;
	public final Color embedColor;

	DiscordChannel(String channel, Color embedColor, String role) {
		this.channel = channel;
		this.embedColor = embedColor;
		this.role = role;
	}
}