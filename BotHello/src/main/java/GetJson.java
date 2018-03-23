import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class GetJson {

	public String getJsonFromServer() throws IOException {

		URL url = new URL("http://localhost:5000/get");

		InputStream is = url.openStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);

		String linha = br.readLine();

		String json = null;
		
		while (linha != null) {

			System.out.println(linha);
			linha = br.readLine();
			json+=linha;
			
		}
		return json;
	}    
}