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
	private final String GET_URL = "https://proxy.webshare.io/api/proxy/list/";
	private final String API_KEY = "691a70280566a3162d755135363409dd32f039d3";

	public List<String> retrieveProxyList() {
		List<String> outputList = new ArrayList<>();
		String proxyList = requestProxyList();

		if (proxyList != null) {
			JsonObject json = new JsonParser().parse(proxyList).getAsJsonObject();

			for (JsonElement curElement : json.getAsJsonArray("results")) {
				JsonObject curJson = curElement.getAsJsonObject();
				outputList.add(curJson.get("proxy_address").toString().replace("\"", "")
						+ ":" + curJson.getAsJsonObject("ports").get("http"));
			}
		}

//		System.out.println(outputList.toString());

		return outputList;
	}

	private String requestProxyList() {
		try {
			URL obj = new URL(GET_URL);
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
			System.out.println("An error occurred while attempting to request a proxy list!");
			ex.printStackTrace();
		}

		return null;
	}
}
