package com.ayserjamshidi.retailscrape;

import com.ayserjamshidi.retailscrape.addons.DiscordWebhook;
import com.ayserjamshidi.retailscrape.website.Newegg;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RetailScrape {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Initializing main thread...");
		System.out.println("Starting main loop.");

		Newegg[] itemList = {
//				new Newegg("AMD Ryzen 5 5600X 6-Core 3.7 GHz Socket AM4 65W 100-100000065BOX Desktop Processor", // 5600x
//						"https://www.newegg.com/amd-ryzen-5-5600x/p/N82E16819113666"),
//				new Newegg("AMD Ryzen 5 5600X 3.7 GHz, OLOy WarHawk RGB 16GB (2 x 8GB) DDR4 3000 (PC4 24000) Desktop Memory",
//						"https://www.newegg.com/Product/ComboDealDetails?ItemList=Combo.4207495"),
//				new Newegg("AMD Ryzen 5 5600X 3.7 GHz Desktop Processor, Crucial Ballistix RGB 16GB (2 x 8GB) DDR4 3600 Desktop Memory",
//						"https://www.newegg.com/Product/ComboDealDetails?ItemList=Combo.4199040"),
//				new Newegg("AMD Ryzen 5 5600X 3.7 GHz Desktop Processor, OLOy 8GB DDR4 3600 Desktop Memory",
//						"https://www.newegg.com/Product/ComboDealDetails?ItemList=Combo.4199041"),
//				new Newegg("AMD Ryzen 5 5600X 3.7 GHz Socket AM4 100-100000065BOX Desktop Processor + WD Black 2TB",
//						"https://www.newegg.com/Product/ComboDealDetails?ItemList=Combo.4199103"),
//				new Newegg("AMD Ryzen 5 5600X 3.7 GHz CPU, OLOy 16GB (2 x 8GB) 288-Pin DDR4 3200 (PC4 25600) Desktop Memory",
//						"https://www.newegg.com/Product/ComboDealDetails?ItemList=Combo.4202091"),
//				new Newegg("ASUS TUF Gaming NVIDIA GeForce RTX 3080", // 3080
//						"https://www.newegg.com/asus-geforce-rtx-3080-tuf-rtx3080-10g-gaming/p/N82E16814126453"),
//				new Newegg("ASUS ROG Strix GeForce RTX 3080",
//						"https://www.newegg.com/asus-geforce-rtx-3080-rog-strix-rtx3080-o10g-gaming/p/N82E16814126457"),
//				new Newegg("ASUS TUF Gaming GeForce RTX 3080",
//						"https://www.newegg.com/asus-geforce-rtx-3080-tuf-rtx3080-o10g-gaming/p/N82E16814126452"),
				new Newegg("AMD Ryzen 9 5950X", // 5950x
						"https://www.newegg.com/amd-ryzen-9-5950x/p/N82E16819113663"),
				new Newegg("GIGABYTE B550 AORUS MASTER Motherboard, AMD Ryzen 9 5950X Processor",
						"https://www.newegg.com/Product/ComboDealDetails?ItemList=Combo.4207303"),
				new Newegg("AMD Ryzen 9 5950X Processor, MSI MPG X570 GAMING PRO CARBON WIFI ATX Gaming Motherboard",
						"https://www.newegg.com/Product/ComboDealDetails?ItemList=Combo.4207303"),
				new Newegg("AMD Ryzen 9 5950X 3.4GHz Socket AM4 Desktop Processor + Seagate 16TB Exos Enterprise HDD",
						"https://www.newegg.com/Product/ComboDealDetails?ItemList=Combo.4199825"),
				new Newegg("GIGABYTE X570 AORUS ELITE Motherboard, AMD Ryzen 9 5950X Processor",
						"https://www.newegg.com/Product/ComboDealDetails?ItemList=Combo.4207317"),
				new Newegg("AMD Ryzen 9 5950X 3.4 GHz Socket AM4 100-100000059WOF Desktop Processor + Seagate FireCuda 520 M.2 2280 2TB PCIe Gen4 x4, NVMe 1.3 3D TLC Internal Solid State Drive (SSD)",
						"https://www.newegg.com/Product/ComboDealDetails?ItemList=Combo.4199061"),
//				new Newegg("ASUS ROG MAXIMUS XII HERO (WI-FI) LGA 1200 (Intel 10th Gen) Intel Z490 (WiFi 6) SATA 6Gb/s ATX Intel Motherboard, Intel Core i7-10700K Processor", "https://www.newegg.com/Product/ComboDealDetails?ItemList=Combo.4205283")
		};
//				"https://www.newegg.com/p/pl?d=asus+3080&PageSize=96&N=100007709&isdeptsrh=1");

		List<Thread> threadList = new ArrayList<>();

		for (Newegg curItem : itemList) {
			Thread currentThread = new Thread(curItem);

			currentThread.start();
			threadList.add(currentThread);
//			Thread.sleep(2000);

			System.out.println("Started thread for " + curItem.threadTitle);
		}

		while (true) { // Infinite loop.  Will stop when all threads are closed.
			boolean keepLooping = false;

			for (Thread curThread : threadList)
				if (curThread.isAlive()) {
					keepLooping = true;
					break;
				} else
					threadList.remove(curThread);

			if (!keepLooping)
				break;

			Thread.sleep(2000);
		}

		System.out.println("All threads are not alive!");
	}
}