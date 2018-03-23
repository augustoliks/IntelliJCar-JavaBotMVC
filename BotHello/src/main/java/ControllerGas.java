import java.io.IOException;

import com.google.gson.JsonSyntaxException;
import com.pengrad.telegrambot.model.Update;

public class ControllerGas implements SearchStrategy {

	private Model model;
	private View view;

	public ControllerGas (Model model, View view) {
		this.model = model;
		this.view = view;
	}

	
	public void process(Update update) throws JsonSyntaxException, IOException {
		view.sendTypingMessage(update);
		model.searchGas(update);
	}
}