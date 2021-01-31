package com.ayserjamshidi.retailscrape.addons.proxything;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AcquireProxyList {
	private final String USER_AGENT = "Mozilla/5.0";
	private final String GET_URL = "https://proxy.webshare.io";
	private String URL_EXTENSION = "/api/proxy/list/?page=1";
	private final String API_KEY = "691a70280566a3162d755135363409dd32f039d3";
	//	private final String GET_URL = "http://api.mudfish.net/graphql?query=%7B%0A%09user%20%7B%0A%09%09vNodeStatuses%20%7B%0A%09%09%09vid%0A%09%09%09location%0A%09%09%09rttavg%0A%09%09%09rttstd%0A%09%09%7D%0A%09%7D%0A%7D";
	private final String GET_URL2 = "http://api.mudfish.net/graphql?query=%7B%0A%09user%20%7B%0A%09%09vNodeStatuses%20%7B%0A%09%09%09vid%0A%09%09%09location%0A%09%09%7D%0A%09%7D%0A%7D";

	public List<String> retrieveProxyList() {
		List<String> outputList = new ArrayList<>();
		String proxyList = requestProxyList();

		while (URL_EXTENSION != null && proxyList != null) {
			JsonObject json = new JsonParser().parse(proxyList).getAsJsonObject();

			for (JsonElement curElement : json.getAsJsonArray("results")) {
				JsonObject curJson = curElement.getAsJsonObject();
				outputList.add(curJson.get("proxy_address").toString().replace("\"", "")
						+ ":" + curJson.getAsJsonObject("ports").get("http"));
			}

			JsonElement nextObject = json.get("next");

			if (!nextObject.isJsonNull()) {
				URL_EXTENSION = nextObject.getAsString();
				proxyList = requestProxyList();
			} else
				URL_EXTENSION = null;
		}

		return outputList;
	}

	private String requestProxyList() {
		try {
			URL obj = new URL(GET_URL + URL_EXTENSION);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", "Token " + API_KEY);
			con.setRequestProperty("User-Agent", USER_AGENT);

			int responseCode = con.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuilder response = new StringBuilder();

				while ((inputLine = in.readLine()) != null)
					response.append(inputLine);

				in.close();

				return response.toString();
			}
		} catch (Exception ex) {
			System.out.println("Error while attempting to query Webshare for proxy list:");
			ex.printStackTrace();
		}

		return null;
	}

	/*public String requestProxyListv2() {
		try {
			URL obj = new URL(GET_URL2);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImxtZmFvb3duIiwibGFuZyI6ImVuX1VTIiwidWlkIjoxNDE1NDksInRva2VuIjoiMjAyMjEzNDI5MSIsInY0X2FkdmFuY2VkX21vZGUiOjEsImFsaWFzX2VuYWJsZSI6MCwidjRfdXNlciI6MCwiYWxpYXNfdWlkIjotMX0.C6qOYbmQ7K_lDS7jyxYMLx87loTQwpxOxekEg36SckY");
			con.setRequestProperty("query", "\"\\n      {\\n        user {\\n          vNodeStatuses {\\n            vid\\n            location\\n            rttavg\\n            rttstd\\n          }\\n        }\\n      }\\n    \"");
			con.setRequestProperty("User-Agent", USER_AGENT);

			int responseCode = con.getResponseCode();

			System.out.println("1");
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				System.out.println("2");
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuilder response = new StringBuilder();

				while ((inputLine = in.readLine()) != null)
					response.append(inputLine);

				in.close();

				return response.toString();
			}
		} catch (Exception ex) {
			System.out.println("An error occurred while attempting to request a proxy list!");
			ex.printStackTrace();
		}

		return null;
	}*/
}
