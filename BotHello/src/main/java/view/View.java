package view;

import java.io.FileNotFoundException;
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

import controller.ControllerBal;
import controller.ControllerBat;
import controller.ControllerDateIndex;
import controller.ControllerGPS;
import controller.ControllerGas;
import controller.ControllerGsm;
import controller.ControllerNet;
import controller.ControllerRegisterCar;
import controller.SearchStrategy;
import model.Model;
import model.ToolBox;

public class View implements Observer {

	public View(Model model, String token) throws FileNotFoundException, IOException {
		this.model = model;
		this.bot = TelegramBotAdapter.build(token);
		this.getDialogues();
	}

	private Model model;
	private TelegramBot bot;

	GetUpdatesResponse updatesResponse;
	SendResponse sendResponse;
	BaseResponse baseResponse;

	private static String msgHello;
	private static String btnGas;
	private static String btnGps;
	private static String btnGsm;
	private static String btnNet;
	private static String btnTsp;
	private static String btnBat;
	private static String btnBal;

	private void getDialogues() throws FileNotFoundException, IOException {
		this.msgHello = ToolBox.loadDialogue("HELLO");
		this.btnGas = ToolBox.loadDialogue("GAS");
		this.btnGps = ToolBox.loadDialogue("GPS");
		this.btnGsm = ToolBox.loadDialogue("GSM");
		this.btnNet = ToolBox.loadDialogue("NET");
		this.btnTsp = ToolBox.loadDialogue("TSP");
		this.btnBat = ToolBox.loadDialogue("BAT");
		this.btnBal = ToolBox.loadDialogue("BAL");
	}

	int queuesIndex = 0;
	int state = 0;
	static final int StateTIME = 1;

	SearchStrategy searchStrategy; // Strategy Pattern -- connection View -> Controller

	boolean searchBehaviour = false;

	public void receiveUsersMessages() throws JsonSyntaxException, IOException {

		updatesResponse = bot.execute(new GetUpdates().limit(100).offset(queuesIndex));
		
		List<Update> updates = updatesResponse.updates();
		System.out.println("Limpando a lista...");
		for (Update update : updates) {
			queuesIndex = update.updateId() + 1;
			System.out.println(update.message().text());
		}
		System.out.println("Lista limpa!\n");

		while (true) {

			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(queuesIndex));
			
			updates = updatesResponse.updates();

			for (Update update : updates) {

				System.out.println("Requisição numero: "+update.updateId());
				System.out.println("Nome do usuário: "+update.message().chat().username());
				System.out.println("Id do usuário: "+update.message().chat().id());
				
				queuesIndex = update.updateId() + 1;

				if (state == StateTIME) {
					setControllerSearch(new ControllerDateIndex(model, this));
					this.searchBehaviour = true;
				}

				else if (update.message().text().equals(null)) {
					continue;
				}

				else if (update.message().text().equals(btnTsp)) {
					sendResponse = bot.execute(
							new SendMessage(update.message().chat().id(), ToolBox.loadDialogue("SEARCH-TIME")));
					state = StateTIME;
				}

				else if (update.message().text().equals(btnBat)) {
					setControllerSearch(new ControllerBat(model, this));
					this.searchBehaviour = true;

				} else if (update.message().text().equals(btnBal)) {
					setControllerSearch(new ControllerBal(model, this));
					this.searchBehaviour = true;

				} else if (update.message().text().equals(btnGas)) {
					setControllerSearch(new ControllerGas(model, this));
					this.searchBehaviour = true;

				} else if (update.message().text().equals(btnGps)) {
					setControllerSearch(new ControllerGPS(model, this));
					this.searchBehaviour = true;

				} else if (update.message().text().equals(btnGsm)) {
					setControllerSearch(new ControllerGsm(model, this));
					this.searchBehaviour = true;

				} else if (update.message().text().equals(btnNet)) {
					setControllerSearch(new ControllerNet(model, this));
					this.searchBehaviour = true;

				} else {
					System.out.println("Ok");
					if (update.message().text().equals("/start")) {
						setControllerSearch(new ControllerRegisterCar(model, this));
						this.searchBehaviour = true;

						sendResponse = bot.execute(new SendMessage(update.message().chat().id(),
								"Hello " + update.message().chat().firstName() + "!!!\n" + msgHello)
										.replyMarkup(new ReplyKeyboardMarkup(new String[] { btnGps, btnGas },
												new String[] { btnBat, btnGsm }, new String[] { btnBal, btnNet },
												new String[] { btnTsp })));

					}
					if (update.callbackQuery() != null) {
						sendResponse = bot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
								update.callbackQuery().data()));
					}
				}

				if (this.searchBehaviour == true) {
					this.callController(update);
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
		this.state = 0;
	}
}