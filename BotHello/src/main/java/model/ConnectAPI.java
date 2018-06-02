package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectAPI {

	public String getJsonFromServer(String uri) throws IOException {

		System.out.println(">>> CLASSE ConnectAPI\n->\tRotina getJsonFromServer: <INDEX> "+uri);

		String json = this.connectServer(ToolBox.loadApi("SERVER_ADDRESS") + uri);

		if (json != null) {
			return json;
		}

		else {
			System.out.println("DEU RUIM");
			String errorAlert = "\"Servers not avaible or Data not validate\"";
			return "{" 
					+ "\"bat\": " + errorAlert + "," 
					+ "\"gas\": " + errorAlert + "," 
					+ "\"gsm\": " + errorAlert + "," 
					+ "\"lat\": " + errorAlert + "," 
					+ "\"lon\": " + errorAlert + "," 
					+ "\"tsp\": " + errorAlert
					+ "}";
		}
	}
	
	public boolean testConnectionServer(String url) throws MalformedURLException, IOException {
		
		
		System.out.println(url);
		
		HttpURLConnection c1 = (HttpURLConnection) new URL(url).openConnection();
        String responseMessage;
		
        responseMessage = c1.getResponseMessage();
		
		if (responseMessage.equals("OK")) {
			return true;
		}
		else {
			return false;
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

		} catch (Exception e) {
			System.out.println("\tERRO in the connection for: " + serverUrl);
			return null;
		}

	}

}