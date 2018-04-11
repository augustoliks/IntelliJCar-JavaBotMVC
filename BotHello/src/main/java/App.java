import java.io.IOException;

import com.google.gson.JsonSyntaxException;

import model.Model;
import model.ToolBox;
import view.View;

public class App { 
	
	private static Model model;
	
	public static void main(String[] args) throws JsonSyntaxException, IOException {
		model = Model.getInstance(); 
		View view = new View(model, ToolBox.loadTelegramKey());
		model.registerObserver(view);
		view.receiveUsersMessages();
	}

}