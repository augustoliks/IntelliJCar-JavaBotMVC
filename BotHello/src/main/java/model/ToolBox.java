package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ToolBox {

	public static String loadTelegramKey() throws FileNotFoundException, IOException {

		Properties prop = new Properties();
		InputStream input = new FileInputStream("src/main/java/resources/telegram.properties");

		prop.load(input);

		return prop.getProperty("TELEGRAM_TOKEN");
	}

	public static String loadDialogue(String dialogue) throws FileNotFoundException, IOException {

		Properties prop = new Properties();
		InputStream input = new FileInputStream("src/main/java/resources/dialogues.properties");

		prop.load(input);

		return prop.getProperty(dialogue);
	}

	public static String loadApi(String server) throws FileNotFoundException, IOException {

		Properties prop = new Properties();
		InputStream input = new FileInputStream("src/main/java/resources/api.properties");

		prop.load(input);

		return prop.getProperty(server);

	}

//  48 = 0	
//  49 = 1
//	50 = 2
//	51 = 3
//	52 = 4
//	53 = 5
//	54 = 6
//	55 = 7
//	56 = 8
//	57 = 9

}
