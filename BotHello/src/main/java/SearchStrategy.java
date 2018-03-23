import java.io.IOException;

import com.google.gson.JsonSyntaxException;
import com.pengrad.telegrambot.model.Update;

public interface SearchStrategy {
	
	public void process(Update update) throws JsonSyntaxException, IOException;
}