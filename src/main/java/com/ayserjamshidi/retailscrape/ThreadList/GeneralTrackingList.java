package com.ayserjamshidi.retailscrape.ThreadList;

import com.ayserjamshidi.retailscrape.addons.discord.DiscordChannel;
import com.ayserjamshidi.retailscrape.searchresults.AmazonSearch;
import com.ayserjamshidi.retailscrape.searchresults.BestBuySearch;
import com.ayserjamshidi.retailscrape.searchresults.NeweggSearch;

public class GeneralTrackingList {
	public Thread[] getNeweggUSA() {
		return new NeweggListUSA().threads;
	}

	public Thread[] getBestBuyUSA() {
		return new BestBuyListUSA().threads;
	}

	public Thread[] getAmazonUSA() { return new AmazonListUSA().threads; }

	public Thread[] getNeweggCA() {
		return new NeweggListCA().threads;
	}

	public Thread[] getTesting() {
		return new TestingList().threads;
	}

	public Thread[] getPersonal() {
		return new PersonalList().threads;
	}
}

class NeweggListUSA {
	public Thread[] threads = {
			//Nvidia GPU
			new NeweggSearch("Nvidia 3090 - Newegg USA", DiscordChannel.NVIDIA_3090_USA, new String[]{
					"https://www.newegg.com/p/pl?d=RTX%203090%20GPU&N=4814%208000%20100006662&PageSize=96&Order=2",
					"https://www.newegg.com/p/pl?d=RTX%203090%20GPU&N=4814%20100006662%208000&PageSize=96&Order=2"
			}),
			new NeweggSearch("Nvidia 3080 - Newegg USA", DiscordChannel.NVIDIA_3080_USA, new String[]{
					"https://www.newegg.com/p/pl?d=RTX%203080%20GPU&N=8000%208000%20100006662%204021%204022&PageSize=96&Order=2",
					"https://www.newegg.com/p/pl?d=RTX%203080%20GPU&N=8000%208000%20100006662&PageSize=96&Order=2",
					"https://www.newegg.com/p/pl?d=RTX%203080%20GPU&N=4022%20100006662%204021%204814%208000%204841&PageSize=96&Order=2",
					"https://www.newegg.com/p/pl?d=RTX%203080%20GPU&N=100006662%208000%204021%204841%204022%204814&PageSize=96&Order=2"
			}),
			new NeweggSearch("Nvidia 3070 - Newegg USA", DiscordChannel.NVIDIA_3070_USA, new String[]{
					"https://www.newegg.com/p/pl?d=RTX%203070%20GPU&N=8000%208000%20100006662&PageSize=96&Order=2",
					"https://www.newegg.com/p/pl?d=RTX%203070%20GPU&N=8000%204814%20100006662&PageSize=96&Order=2",
					"https://www.newegg.com/p/pl?d=RTX%203070%20GPU&N=4814%20100006662%208000&PageSize=96&Order=2"
			}),
			new NeweggSearch("Nvidia 3060ti - Newegg USA", DiscordChannel.NVIDIA_3060TI_USA, new String[]{
					"https://www.newegg.com/p/pl?d=RTX%203060%20Ti%20GPU&N=8000%208000%20100006662&PageSize=96&Order=2",
					"https://www.newegg.com/p/pl?d=RTX%203060%20Ti%20GPU&N=8000%20100006662%204814&PageSize=96&Order=2"
			}),

			// AMD GPU
			new NeweggSearch("AMD 6900XT - Newegg USA", DiscordChannel.AMD_RX6900XT_USA, new String[]{
					"https://www.newegg.com/p/pl?d=6900xt%20GPU&N=8000%20100006662&PageSize=96&Order=2"
			}),
			new NeweggSearch("AMD 6800XT - Newegg USA", DiscordChannel.AMD_RX6800XT_USA, new String[]{
					"https://www.newegg.com/p/pl?d=6800xt%20GPU&N=8000%20100006662&PageSize=96&Order=2"
			}),
			new NeweggSearch("AMD 6800 - Newegg USA", DiscordChannel.AMD_RX6800_USA, new String[]{
					"https://www.newegg.com/p/pl?d=rx6800%20GPU%20-xt&N=8000%20100006662&PageSize=96&Order=2"
			}),

			// AMD CPU
			new NeweggSearch("AMD 5950X - Newegg USA", DiscordChannel.AMD_5950x_USA, new String[]{
					"https://www.newegg.com/p/pl?d=100-100000059WOF&N=100006676%204814%208000&PageSize=96&Order=2"
			}),
			new NeweggSearch("AMD 5900X - Newegg USA", DiscordChannel.AMD_5900x_USA, new String[]{
					"https://www.newegg.com/p/pl?d=100-100000061WOF&N=100006676%204814%208000&PageSize=96&Order=2"
			}),
			new NeweggSearch("AMD 5800X - Newegg USA", DiscordChannel.AMD_5800x_USA, new String[]{
					"https://www.newegg.com/p/pl?d=100-100000063WOF&N=100006676%204814%208000&PageSize=96&Order=2",
					"https://www.newegg.com/p/pl?d=100-100000063WOF&N=8000%20100006676%204814&PageSize=96&Order=2",
					"https://www.newegg.com/p/pl?d=100-100000063WOF&N=100006676%208000%204814&PageSize=96&Order=2"
			}),
			new NeweggSearch("AMD 5600X - Newegg USA", DiscordChannel.AMD_5600x_USA, new String[]{
					"https://www.newegg.com/p/pl?d=100-100000065BOX&N=100006676%204814%208000&PageSize=96&Order=2",
					"https://www.newegg.com/p/pl?d=100-100000065BOX&N=8000%20100006676%204814&PageSize=96&Order=2",
					"https://www.newegg.com/p/pl?d=100-100000065BOX&N=100006676%208000%204814&PageSize=96&Order=2"
			}),
	};
}

class NeweggListCA {
	public Thread[] threads = {
			//Nvidia GPU
			new NeweggSearch("Nvidia 3090 - Newegg Canada", DiscordChannel.NVIDIA_3090_CA, new String[]{
					"https://www.newegg.ca/p/pl?d=RTX+3090+GPU&N=4814%208000&Order=2&PageSize=96"
			}),
			new NeweggSearch("Nvidia 3080 - Newegg Canada", DiscordChannel.NVIDIA_3080_CA, new String[]{
					"https://www.newegg.ca/p/pl?d=RTX+3080+GPU&N=4814%208000&Order=2&PageSize=96"
			}),
			new NeweggSearch("Nvidia 3070 - Newegg Canada", DiscordChannel.NVIDIA_3070_CA, new String[]{
					"https://www.newegg.ca/p/pl?d=RTX+3070+GPU&N=4814%208000&Order=2&PageSize=96"
			}),
			new NeweggSearch("Nvidia 3060ti - Newegg Canada", DiscordChannel.NVIDIA_3060TI_CA, new String[]{
					"https://www.newegg.ca/p/pl?d=RTX+3060TI+GPU&N=4814%208000&Order=2&PageSize=96"
			}),

			// AMD GPU
			new NeweggSearch("AMD 6900XT - Newegg Canada", DiscordChannel.AMD_RX6900XT_CA, new String[]{
					"https://www.newegg.ca/p/pl?d=6900XT+GPU&N=4814%208000&Order=2&PageSize=96"
			}),
			new NeweggSearch("AMD 6800XT - Newegg Canada", DiscordChannel.AMD_RX6800XT_CA, new String[]{
					"https://www.newegg.ca/p/pl?d=6800XT+GPU&N=4814%208000&Order=2&PageSize=96"
			}),
			new NeweggSearch("AMD 6800 - Newegg Canada", DiscordChannel.AMD_RX6800_CA, new String[]{
					"https://www.newegg.ca/p/pl?d=6800%20-XT+GPU&N=4814%208000&Order=2&PageSize=96"
			}),

			// AMD CPU
			new NeweggSearch("AMD 5950X - Newegg Canada", DiscordChannel.AMD_5950x_CA, new String[]{
					"https://www.newegg.ca/p/pl?d=100-100000059WOF&N=4814%208000&Order=2&PageSize=96"
			}),
			new NeweggSearch("AMD 5900X - Newegg Canada", DiscordChannel.AMD_5900x_CA, new String[]{
					"https://www.newegg.ca/p/pl?d=100-100000061WOF&N=4814%208000&Order=2&PageSize=96"
			}),
			new NeweggSearch("AMD 5800X - Newegg Canada", DiscordChannel.AMD_5800x_CA, new String[]{
					"https://www.newegg.ca/p/pl?d=100-100000063WOF&N=4814%208000&Order=2&PageSize=96"
			}),
			new NeweggSearch("AMD 5600X - Newegg Canada", DiscordChannel.AMD_5600x_CA, new String[]{
					"https://www.newegg.ca/p/pl?d=100-100000065BOX&N=4814%208000&Order=2&PageSize=96"
			}),
	};
}

class BestBuyListUSA {
	public Thread[] threads = {
			// Nvidia GPU
			new BestBuySearch("Nvidia 3090 - BestBuy USA", DiscordChannel.NVIDIA_3090_USA, new String[]{
					"https://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&id=pcat17071&iht=y&keys=keys&ks=960&list=n&qp=category_facet%3DGPUs%20%2F%20Video%20Graphics%20Cards~abcat0507002&sc=Global&st=rtx%203090&type=page&usc=All%20Categories"
			}),
			new BestBuySearch("Nvidia 3080 - BestBuy USA", DiscordChannel.NVIDIA_3080_USA, new String[]{
					"https://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&id=pcat17071&iht=y&keys=keys&ks=960&list=n&qp=category_facet%3DGPUs%20%2F%20Video%20Graphics%20Cards~abcat0507002&sc=Global&st=rtx%203080&type=page&usc=All%20Categories"
			}),
			new BestBuySearch("Nvidia 3070 - BestBuy USA", DiscordChannel.NVIDIA_3070_USA, new String[]{
					"https://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&id=pcat17071&iht=y&keys=keys&ks=960&list=n&qp=category_facet%3DGPUs%20%2F%20Video%20Graphics%20Cards~abcat0507002&sc=Global&st=rtx%203070&type=page&usc=All%20Categories"
			}),
			new BestBuySearch("Nvidia 3060ti - BestBuy USA", DiscordChannel.NVIDIA_3060TI_USA, new String[]{
					"https://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&id=pcat17071&iht=y&keys=keys&ks=960&list=n&qp=category_facet%3DGPUs%20%2F%20Video%20Graphics%20Cards~abcat0507002&sc=Global&st=rtx%203060%20ti&type=page&usc=All%20Categories"
			}),

			// AMD GPU
			new BestBuySearch("AMD 6900XT - BestBuy USA", DiscordChannel.AMD_RX6900XT_USA, new String[]{
					"https://www.bestbuy.com/site/searchpage.jsp?st=amd+6900+gpu&_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=&sp=&qp=&list=n&af=true&iht=y&usc=All+Categories&ks=960&keys=keys"
			}),
			new BestBuySearch("AMD 6800XT - BestBuy USA", DiscordChannel.AMD_RX6800XT_USA, new String[]{
					"https://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&id=pcat17071&iht=y&keys=keys&ks=960&list=n&qp=category_facet%3DGPUs%20%2F%20Video%20Graphics%20Cards~abcat0507002%5Egpusv_facet%3DGraphics%20Processing%20Unit%20(GPU)~AMD%20Radeon%20RX%206800%20XT&sc=Global&st=amd%206800&type=page&usc=All%20Categories"
			}),
			new BestBuySearch("AMD 6800 - BestBuy USA", DiscordChannel.AMD_RX6800_USA, new String[]{
					"https://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&id=pcat17071&iht=y&keys=keys&ks=960&list=n&qp=category_facet%3DGPUs%20%2F%20Video%20Graphics%20Cards~abcat0507002%5Egpusv_facet%3DGraphics%20Processing%20Unit%20(GPU)~AMD%20Radeon%20RX%206800&sc=Global&st=amd%206800&type=page&usc=All%20Categories"
			}),

			// CPU
			new BestBuySearch("AMD 5950X - BestBuy USA", DiscordChannel.AMD_5950x_USA, new String[]{
					"https://www.bestbuy.com/site/searchpage.jsp?st=5950x&_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=&sp=&qp=&list=n&af=true&iht=y&usc=All+Categories&ks=960&keys=keys"
			}),
			new BestBuySearch("AMD 5900X - BestBuy USA", DiscordChannel.AMD_5900x_USA, new String[]{
					"https://www.bestbuy.com/site/searchpage.jsp?st=5900x&_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=&sp=&qp=&list=n&af=true&iht=y&usc=All+Categories&ks=960&keys=keys"
			}),
			new BestBuySearch("AMD 5800X - BestBuy USA", DiscordChannel.AMD_5800x_USA, new String[]{
					"https://www.bestbuy.com/site/searchpage.jsp?st=5800x&_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=&sp=&qp=&list=n&af=true&iht=y&usc=All+Categories&ks=960&keys=keys"
			}),
			new BestBuySearch("AMD 5600X - BestBuy USA", DiscordChannel.AMD_5600x_USA, new String[]{
					"https://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&id=pcat17071&iht=y&keys=keys&ks=960&list=n&qp=category_facet%3DCPUs%20%2F%20Processors~abcat0507010&sc=Global&st=5600x&type=page&usc=All%20Categories"
			}),
	};
}

class BestBuyListCA {
	public Thread[] threads = {
			// Nvidia GPU
			new BestBuySearch("Nvidia 3090 - BestBuy Canada", DiscordChannel.NVIDIA_3090_CA, new String[]{
					"https://www.bestbuy.ca/en-ca/collection/rtx-30-series-graphic-cards/316108?path=category%253AComputers%2B%2526%2BTablets%253Bcategory%253APC%2BComponents%253Bcategory%253AGraphics%2BCards%253Bcustom0graphicscardtype%253AGeForce%2BRTX%2B3090%253Bsoldandshippedby0enrchstring%253ABest%2BBuy&sort=priceHighToLow"
			}),
			new BestBuySearch("Nvidia 3080 - BestBuy Canada", DiscordChannel.NVIDIA_3080_CA, new String[]{
					"https://www.bestbuy.ca/en-ca/collection/rtx-30-series-graphic-cards/316108?path=category%253AComputers%2B%2526%2BTablets%253Bcategory%253APC%2BComponents%253Bcategory%253AGraphics%2BCards%253Bcustom0graphicscardtype%253AGeForce%2BRTX%2B3080%253Bsoldandshippedby0enrchstring%253ABest%2BBuy&sort=priceHighToLow"
			}),
			new BestBuySearch("Nvidia 3070 - BestBuy Canada", DiscordChannel.NVIDIA_3070_CA, new String[]{
					"https://www.bestbuy.ca/en-ca/collection/rtx-30-series-graphic-cards/316108?path=category%253AComputers%2B%2526%2BTablets%253Bcategory%253APC%2BComponents%253Bcategory%253AGraphics%2BCards%253Bcustom0graphicscardtype%253AGeForce%2BRTX%2B3070%253Bsoldandshippedby0enrchstring%253ABest%2BBuy&sort=priceHighToLow"
			}),
			new BestBuySearch("Nvidia 3060ti - BestBuy Canada", DiscordChannel.NVIDIA_3060TI_CA, new String[]{
					"https://www.bestbuy.ca/en-ca/search?path=soldandshippedby0enrchstring%253ABest%2BBuy&search=NVIDIA+3060+Ti+-3070+-3080+-3090+-Desktop&sort=priceHighToLow"
			}),

			// AMD GPU
//			new BestBuySearch("AMD 6900XT - BestBuy USA", DiscordChannel.AMD_RX6900XT_USA, new String[]{
//					"https://www.bestbuy.com/site/searchpage.jsp?st=amd+6900+gpu&_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=&sp=&qp=&list=n&af=true&iht=y&usc=All+Categories&ks=960&keys=keys"
//			}),
//			new BestBuySearch("AMD 6800XT - BestBuy USA", DiscordChannel.AMD_RX6800XT_USA, new String[]{
//					"https://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&id=pcat17071&iht=y&keys=keys&ks=960&list=n&qp=category_facet%3DGPUs%20%2F%20Video%20Graphics%20Cards~abcat0507002%5Egpusv_facet%3DGraphics%20Processing%20Unit%20(GPU)~AMD%20Radeon%20RX%206800%20XT&sc=Global&st=amd%206800&type=page&usc=All%20Categories"
//			}),
//			new BestBuySearch("AMD 6800 - BestBuy USA", DiscordChannel.AMD_RX6800_USA, new String[]{
//					"https://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&id=pcat17071&iht=y&keys=keys&ks=960&list=n&qp=category_facet%3DGPUs%20%2F%20Video%20Graphics%20Cards~abcat0507002%5Egpusv_facet%3DGraphics%20Processing%20Unit%20(GPU)~AMD%20Radeon%20RX%206800&sc=Global&st=amd%206800&type=page&usc=All%20Categories"
//			}),

			// CPU
//			new BestBuySearch("AMD 5950X - BestBuy USA", DiscordChannel.AMD_5950x_USA, new String[]{
//					"https://www.bestbuy.com/site/searchpage.jsp?st=5950x&_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=&sp=&qp=&list=n&af=true&iht=y&usc=All+Categories&ks=960&keys=keys"
//			}),
//			new BestBuySearch("AMD 5900X - BestBuy USA", DiscordChannel.AMD_5900x_USA, new String[]{
//					"https://www.bestbuy.com/site/searchpage.jsp?st=5900x&_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=&sp=&qp=&list=n&af=true&iht=y&usc=All+Categories&ks=960&keys=keys"
//			}),
//			new BestBuySearch("AMD 5800X - BestBuy USA", DiscordChannel.AMD_5800x_USA, new String[]{
//					"https://www.bestbuy.com/site/searchpage.jsp?st=5800x&_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=&sp=&qp=&list=n&af=true&iht=y&usc=All+Categories&ks=960&keys=keys"
//			}),
//			new BestBuySearch("AMD 5600X - BestBuy USA", DiscordChannel.AMD_5600x_USA, new String[]{
//					"https://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&id=pcat17071&iht=y&keys=keys&ks=960&list=n&qp=category_facet%3DCPUs%20%2F%20Processors~abcat0507010&sc=Global&st=5600x&type=page&usc=All%20Categories"
//			}),
	};
}

class AmazonListUSA {
	public Thread[] threads = {
			new AmazonSearch("AMD 5950X - Amazon USA", DiscordChannel.AMD_5950x_USA, AmazonTrackingList.AMD_5950X),
			new AmazonSearch("AMD 5900X - Amazon USA", DiscordChannel.AMD_5900x_USA, AmazonTrackingList.AMD_5900X),
			new AmazonSearch("AMD 5800X - Amazon USA", DiscordChannel.AMD_5800x_USA, AmazonTrackingList.AMD_5800X),
			new AmazonSearch("AMD 5600X - Amazon USA", DiscordChannel.AMD_5600x_USA, AmazonTrackingList.AMD_5600X),
	};
}

class PersonalList {
	public Thread[] threads = {
			new NeweggSearch("Testing 3080 - Newegg USA", DiscordChannel.ADMIN_ERRORS, new String[]{
					"https://www.newegg.com/p/pl?d=RTX%203080%20GPU&N=8000%208000%20100006662%204021%204022&PageSize=96",
					"https://www.newegg.com/p/pl?d=RTX%203080%20GPU&N=8000%208000%20100006662&PageSize=96",
					"https://www.newegg.com/p/pl?d=RTX%203080%20GPU&N=4022%20100006662%204021%204814%208000%204841&PageSize=96",
					"https://www.newegg.com/p/pl?d=RTX%203080%20GPU&N=100006662%208000%204021%204841%204022%204814&PageSize=96"
			}),
			new NeweggSearch("Testing 3060ti - Newegg USA", DiscordChannel.ADMIN_ERRORS, new String[]{
					"https://www.newegg.com/p/pl?d=RTX%203060%20Ti0%20GPU&N=8000%208000%20100006662&PageSize=96",
					"https://www.newegg.com/p/pl?d=RTX%203060%20Ti0%20GPU&N=8000%20100006662%204814&PageSize=96"
			}),
	};
}

class TestingList {
	public Thread[] threads = {
//			new NeweggSearch("Testing - Newegg USA", DiscordChannel.AMD_5600x_CA, new String[]{
//					"https://www.newegg.com/global/uk-en/p/pl?d=intel+processor&Order=2&N=101582716&isdeptsrh=1"
//			}),
//			new BestBuySearch("AMD 5600X - BestBuy USA", DiscordChannel.ADMIN_ERRORS, new String[]{
//					"https://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&id=pcat17071&iht=y&keys=keys&ks=960&list=n&qp=category_facet%3DCPUs%20%2F%20Processors~abcat0507010&sc=Global&st=5600x&type=page&usc=All%20Categories"
//			}),
			new AmazonSearch("AMD 5900X - Amazon USA", DiscordChannel.AMD_5600x_CA, AmazonTrackingList.AMD_5900X),
	};
}