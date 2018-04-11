package controller;

import java.io.IOException;

import com.google.gson.JsonSyntaxException;
import com.pengrad.telegrambot.model.Update;

import model.Model;
import view.View;

public class ControllerNet implements SearchStrategy {

	private Model model;
	private View view;

	public ControllerNet (Model model, View view) {
		this.model = model;
		this.view = view;
	}

	public void process(Update update) throws JsonSyntaxException, IOException {
		view.sendTypingMessage(update);
		model.searchNet(update);		
	}
}