import java.io.IOException;

import com.google.gson.JsonSyntaxException;
import com.pengrad.telegrambot.model.Update;

public class ControllerGPS implements SearchStrategy {

	private Model model;
	private View view;

	public ControllerGPS (Model model, View view) {
		this.model = model;
		this.view = view;
	}

	
	public void process(Update update) throws JsonSyntaxException, IOException {
		view.sendTypingMessage(update);
		model.searchGPS(update);
		
	}
}