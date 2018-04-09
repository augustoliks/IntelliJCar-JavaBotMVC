import java.io.IOException;
import java.util.List;

import com.vdurmont.emoji.EmojiParser;

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

	private static String hello = "I am @JCAR_BOT " + new Emoji().getEmoji("jcar")
			+ "\nYour facilitator of access to your car information, my operation is quite simple, just click on the option you want to know the real time information, or choose the option \"search time for results\" to know information time of day";
	private static String gas = "Gasoline " + new Emoji().getEmoji("gas");
	private static String gps = "GPS " + new Emoji().getEmoji("gps");
	private static String gsm = "Jcar Nivel " + new Emoji().getEmoji("gsm");
	private static String net = "Internet\nFranchise Avaible" + new Emoji().getEmoji("net");
	private static String tsp = "Search Time for results " + new Emoji().getEmoji("tsp");
	private static String bat = "Battery " + new Emoji().getEmoji("bat");
	private static String bal = "Balance Avaible " + new Emoji().getEmoji("bal");

	int queuesIndex = 0;
	int state = 0;
	static final int StateTIME = 1;

	SearchStrategy searchStrategy; // Strategy Pattern -- connection View -> Controller

	boolean searchBehaviour = false;

	public void receiveUsersMessages() throws JsonSyntaxException, IOException {

		while (true) {

			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(queuesIndex));

			List<Update> updates = updatesResponse.updates();

			for (Update update : updates) {

				queuesIndex = update.updateId() + 1;

				if (state == StateTIME) {
					setControllerSearch(new ControllerDateIndex(model, this));
					this.searchBehaviour = true;
				}

				else if (update.message().text().equals(tsp)) {
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),
							"Insert Hour and minute for a search data\n\nExample, inserting 1013 for look data by 10 hour and 13 minutes: "));
					state = StateTIME;
				}

				else if (update.message().text().equals(bat)) {
					setControllerSearch(new ControllerBat(model, this));
					this.searchBehaviour = true;

				} else if (update.message().text().equals(bal)) {
					setControllerSearch(new ControllerBal(model, this));
					this.searchBehaviour = true;

				} else if (update.message().text().equals(gas)) {
					setControllerSearch(new ControllerGas(model, this));
					this.searchBehaviour = true;

				} else if (update.message().text().equals(gps)) {
					setControllerSearch(new ControllerGPS(model, this));
					this.searchBehaviour = true;

				} else if (update.message().text().equals(gsm)) {
					setControllerSearch(new ControllerGsm(model, this));
					this.searchBehaviour = true;

				} else if (update.message().text().equals(net)) {
					setControllerSearch(new ControllerNet(model, this));
					this.searchBehaviour = true;

				} else {
					System.out.println("Ok");
					if (update.message().text().equals("/start")) {
						model.registerCar(update);
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(),
								"Hello " + update.message().chat().firstName() + "!!!\n" + hello).replyMarkup(
										new ReplyKeyboardMarkup(new String[] { gps, gas }, new String[] { bat, gsm },
												new String[] { bal, net }, new String[] { tsp })));

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