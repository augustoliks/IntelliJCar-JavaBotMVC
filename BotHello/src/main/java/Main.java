import java.io.IOException;

import com.google.gson.JsonSyntaxException;

public class Main { 
	
	private static Model model;

	public static void main(String[] args) throws JsonSyntaxException, IOException {
		model = Model.getInstance(); 
		View view = new View(model, "551399495:AAFtL-krvdG7dLkxSfTfLB2Jb-uteDhwNGQ");
		model.registerObserver(view);
		view.receiveUsersMessages();
	}

}