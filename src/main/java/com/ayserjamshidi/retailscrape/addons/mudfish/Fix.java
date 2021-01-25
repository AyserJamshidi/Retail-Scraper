package com.ayserjamshidi.retailscrape.addons.mudfish;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fix {
//	static final String popupConnectUrl = "chrome-extension://oaoiiaanhkibhodklpnkmnaggcagkidc/html/popup_connect.html";

	public static void thing(final WebDriver driver) {
		driver.get("chrome-extension://oaoiiaanhkibhodklpnkmnaggcagkidc/html/popup_connect.html");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.get("chrome-extension://oaoiiaanhkibhodklpnkmnaggcagkidc/html/popup_connect.html");
//		driver.navigate().refresh(); // TODO Make sure this still works instead of using another driver.get

		driver.findElement(By.id("mudfish_conf_options")).click();
		driver.findElement(By.id("remember_me")).click();
		driver.findElement(By.id("username")).sendKeys("Lmfaoown");
		driver.findElement(By.id("password")).sendKeys("Hello123123!");
		driver.findElement(By.id("popup_connect_save")).click();

		final WebElement nodeSelectElement = driver.findElement(By.id("staticnodes_select"));
		final List<String> allowedServers = new ArrayList<>();

		for (final WebElement ele : nodeSelectElement.findElements(By.tagName("option"))) {
			if (ele.getText().contains("US East")
					|| ele.getText().contains("US West")
					|| ele.getText().contains("US Central")
					|| ele.getText().contains("CA East")) {
				allowedServers.add(ele.getAttribute("value"));
			}
		}

		new Select(nodeSelectElement).selectByValue(allowedServers.get(new Random().nextInt(allowedServers.size() - 1)));

		try {
			Thread.sleep(5000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

		final ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.close();
		driver.switchTo().window(tabs.get(0));
	}
}