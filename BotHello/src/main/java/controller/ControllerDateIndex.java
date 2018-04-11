package controller;

import java.io.IOException;

import com.google.gson.JsonSyntaxException;
import com.pengrad.telegrambot.model.Update;

import model.Model;
import view.View;

public class ControllerDateIndex implements SearchStrategy {

	private Model model;
	private View view;

	public ControllerDateIndex (Model model, View view) {
		this.model = model;
		this.view = view;
	}

	
	public void process(Update update) throws JsonSyntaxException, IOException {
		view.sendTypingMessage(update);
		model.searchTime(update);
		
	}
}