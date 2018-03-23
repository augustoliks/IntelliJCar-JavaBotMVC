import java.io.IOException;
import java.util.List;

import com.google.gson.JsonSyntaxException;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

public class View implements Observer {

	private Model model;
	private TelegramBot bot;

	public View(Model model, String token) {
		this.model = model;
		this.bot = TelegramBotAdapter.build(token);
	}

	GetUpdatesResponse updatesResponse;
	SendResponse sendResponse;
	BaseResponse baseResponse;

	int queuesIndex = 0;

	SearchStrategy searchStrategy; // Strategy Pattern -- connection View -> Controller

	boolean searchBehaviour = false;

	public void receiveUsersMessages() throws JsonSyntaxException, IOException {

		while (true) {

			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(queuesIndex));

			List<Update> updates = updatesResponse.updates();

			for (Update update : updates) {

				queuesIndex = update.updateId() + 1;

				if (update.callbackQuery() != null) {
					sendResponse = bot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
							update.callbackQuery().data()));
				}

				if (this.searchBehaviour == true) {
					this.callController(update);
				}

				else if (update.message().text().equals("Battery")) {
					setControllerSearch(new ControllerBattery(model, this));
					this.searchBehaviour = true;

				} else if (update.message().text().equals("GPS")) {
					setControllerSearch(new ControllerGPS(model, this));
					this.searchBehaviour = true;

				} else if (update.message().text().equals("Temperature")) {
					setControllerSearch(new ControllerTemperature(model, this));
					this.searchBehaviour = true;

				} else if (update.message().text().equals("Gasoline")) {
					setControllerSearch(new ControllerGas(model, this));
					this.searchBehaviour = true;

				} else {
					System.out.println("Ok");
					if (update.message().text().equals("/start")) {
						model.registerCar(update);
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(),
								"Ola " + update.message().chat().firstName()
										+ " eu vou falar o que você quer saber do ser carro")
												.replyMarkup(new ReplyKeyboardMarkup(new String[] { "GPS", "Gasoline" },
														new String[] { "Temperature", "Battery" })));

					}
				}
			}

		}

	}

	public void setControllerSearch(SearchStrategy controllerSearch) { // Strategy Pattern
		this.searchStrategy = controllerSearch;
	}

	public void callController(Update update) throws JsonSyntaxException, IOException {
		this.searchStrategy.process(update);
	}

	public void sendTypingMessage(Update update) {
		baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
	}

	@Override
	public void updateObserver(long chatId, String data) {
		sendResponse = bot.execute(new SendMessage(chatId, data));
		this.searchBehaviour = false;
	}
}