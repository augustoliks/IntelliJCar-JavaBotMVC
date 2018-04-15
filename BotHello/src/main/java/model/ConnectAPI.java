package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class ConnectAPI {

	public String getJsonFromServer(String index) throws IOException {
		String uri;

		System.out.println(">>> CLASSE ConnectAPI\n->\tRotina getJsonFromServer: <INDEX>"+index);
		
		if (index.equals("now")) {
			uri = "get/";
		} else {
			uri = "get/id=" + index;
		}

		String herokuJson = this.connectServer(ToolBox.loadApi("HEROKU") + uri);
 
		if (herokuJson != null) {

			return herokuJson;
		}

		String raspberryJson = this.connectServer(ToolBox.loadApi("RASPBERRY") + uri);

		if (raspberryJson != null) {
			return raspberryJson;
		}

		else {
			System.out.println("DEU RUIM");
			String errorAlert = "\"Servers not avaible or Data not validate\"";
			return "{" + "\"bat\": " + errorAlert + "," + "\"dad\": " + errorAlert + "," + "\"gas\": " + errorAlert
					+ "," + "\"gsm\": " + errorAlert + "," + "\"lat\": " + errorAlert + "," + "\"lon\": " + errorAlert
					+ "," + "\"maps\": " + errorAlert + "," + "\"sal\": " + errorAlert + "," + "\"tsp\": " + errorAlert
					+ "}";
		}
	}

	private String connectServer(String serverUrl) throws IOException {

		try {
			URL url = new URL(serverUrl);
	
			System.out.println("->\tRotina connectServer:\n->\tTry connection:" + serverUrl);

			InputStream is = url.openStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String linha = br.readLine();

			String json = "";

			while (linha != null) {
				json += linha;
				linha = br.readLine();
			}

			System.out.println("\tReturn from Server:\n\n " + json);

			return json;

		} catch (Exception NullPointerException) {
			System.out.println("\tERRO in the connection for: " + serverUrl);
			return null;
		}

	}

}