import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class ConnectAPI {

	public String getJsonFromServer(String index) throws IOException {
		String uri;

		if (index.equals("mostRecent")) {
			uri = "get/";
		} else {
			uri = "get/id=" + index;
		}

		String herokuJson = this.connectServer("xhttps://jcar-eng.herokuapp.com/" + uri);

		if (herokuJson != null) {
			return herokuJson;
		}

		String raspberryJson = this.connectServer("http://jcar.ddns.net:5000/" + uri);

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

			System.out.println("Try connection:" + serverUrl);

			InputStream is = url.openStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String linha = br.readLine();

			String json = "";

			while (linha != null) {
				json += linha;
				linha = br.readLine();
			}

			System.out.println("Return from Server:\n " + json);

			return json;

		} catch (Exception NullPointerException) {
			System.out.println("ERRO in the connection for: " + serverUrl);
			return null;
		}

	}

}