package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.pengrad.telegrambot.model.Update;

import view.Observer;

public class Model implements Subject {

	private List<Observer> observers = new LinkedList<Observer>();
	private List<Car> cars = new ArrayList<>();

	private static Model uniqueInstance;

	private String time = "now";
	private String startTime = null;
	private String endTime = null;

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void notifyObservers(long chatId, String data) {
		for (Observer observer : observers) {
			observer.updateObserver(chatId, data);
		}
	}

	public static Model getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Model();
		}
		return uniqueInstance;
	}

	public void addCars(Car car) {
		this.cars.add(car);
	}

	public void searchTime(Update update) throws FileNotFoundException, IOException {

		System.out.println("estou em time");

		String data = null;

		if (update.message().text().equals("now")) {
			this.time = "now";
			data = "Results for the time: " + time;
		} else if (update.message().text().length() == 4) {
			this.time = update.message().text();
			data = "Results for the time: " + time;
		}

		else if (update.message().text().length() == 9) {
			String time[] = update.message().text().split("-");
			this.startTime = time[0];
			this.endTime = time[1];
			this.time = startTime + endTime;
			data = "Results for the time: " + time[0] + " to " + time[1];

		} else {
			data = ToolBox.loadDialogue("DATA-NOT-VALID");
		}

		this.notifyObservers(update.message().chat().id(), data);
		System.out.println("Enviei isso: " + data + " -> " + update.message().chat().username());

	}

	public void searchBat(Update update) throws JsonSyntaxException, IOException {
		this.populityDatas(update);

		System.out.println("Estou em Bateria");
		String data = null;

		for (Car car : cars) {
			if (car.getId() == update.message().chat().id()) {
				data = car.getBat();
			}
		}

		if (data != null) {
			this.notifyObservers(update.message().chat().id(), data);
			System.out.println("Enviei isso: " + data + " -> " + update.message().chat().username());
		} else {
			this.notifyObservers(update.message().chat().id(), "NOT FOUND(car not registred)");
		}
	}

	public void searchBal(Update update) throws JsonSyntaxException, IOException {
		this.populityDatas(update);

		System.out.println("Estou em Saldo");
		String data = null;
		for (Car car : cars) {

			if (car.getId() == update.message().chat().id()) {
				data = car.getBat();
			}
		}

		if (data != null) {
			this.notifyObservers(update.message().chat().id(), data);
			System.out.println("Enviei isso: " + data + " -> " + update.message().chat().username());
		} else {
			this.notifyObservers(update.message().chat().id(), "NOT FOUND(car not registred)");
		}

	}

	public void searchGPS(Update update) throws JsonSyntaxException, IOException {
		this.populityDatas(update);
		System.out.println("Estou em GPS");
		String data = null;

		if (time.equals("now")) {
			for (Car car : cars) {
				if (car.getId() == update.message().chat().id()) {
					data = "Your car's status: " + ToolBox.loadApi("GPS-NOW") + "\n\nLocalization in Google Maps: "
							+ car.getMaps();
				}
			}
		} else if (time.length() == 4) {
			for (Car car : cars) {
				if (car.getId() == update.message().chat().id()) {
					data = "Your car's status: " + ToolBox.loadApi("GPS-NOW") + "\n\nLocalization in Google Maps: "
							+ car.getMaps();
				}
			}
		} else {
			for (Car car : cars) {
				if (car.getId() == update.message().chat().id()) {
					data = "Your car's status: " + ToolBox.loadApi("GPS-TIME") + "start=" + this.startTime + "&"
							+ "end=" + this.endTime;
				}
			}
		}
		if (data != null) {
			this.notifyObservers(update.message().chat().id(), data);
			System.out.println("Enviei isso: " + data + " -> " + update.message().chat().username());
		} else {
			this.notifyObservers(update.message().chat().id(), "NOT FOUND(car not registred)");
		}

	}

	public void searchGas(Update update) throws JsonSyntaxException, IOException {
		this.populityDatas(update);
		System.out.println("Estou em Gasolina");
		String data = null;

		for (Car car : cars) {

			if (car.getId() == update.message().chat().id()) {
				data = car.getGas();
			}
		}

		if (data != null) {
			this.notifyObservers(update.message().chat().id(), data);
			System.out.println("Enviei isso: " + data + " -> " + update.message().chat().username());

		} else {
			this.notifyObservers(update.message().chat().id(), "NOT FOUND(car not registred)");
		}
	}

	public void searchGsm(Update update) throws JsonSyntaxException, IOException {
		this.populityDatas(update);
		System.out.println("Estou em Gsm");
		String data = null;

		for (Car car : cars) {

			if (car.getId() == update.message().chat().id()) {
				data = car.getGsm();
			}
		}

		if (data != null) {
			this.notifyObservers(update.message().chat().id(), data);
			System.out.println("Enviei isso: " + data + " -> " + update.message().chat().username());

		} else {
			this.notifyObservers(update.message().chat().id(), "NOT FOUND(car not registred)");
		}

	}

	public void searchNet(Update update) throws JsonSyntaxException, IOException {
		this.populityDatas(update);
		System.out.println("Estou em Net");
		String data = null;

		for (Car car : cars) {

			if (car.getId() == update.message().chat().id()) {
				data = car.getDad();
			}
		}

		if (data != null) {
			this.notifyObservers(update.message().chat().id(), data);
			System.out.println("Enviei isso: " + data + " -> " + update.message().chat().username());

		} else {
			this.notifyObservers(update.message().chat().id(), "NOT FOUND(car not registred)");
		}

	}

	public void populityDatas(Update update) throws JsonSyntaxException, IOException {
		System.out.println("Atualizando os dados");

		Gson gson = new Gson();

		for (int k = 0; k < cars.size(); k++) {

			if (cars.get(k).getId() == update.message().chat().id()) {

				Car c = new Car(update.message().chat().id());
				c = gson.fromJson(new ConnectAPI().getJsonFromServer(this.time).toString(), Car.class);
				c.setId(update.message().chat().id());

				cars.remove(k);
				cars.add(k, c);
			}

		}

	}

	public void registerCar(Update update) {
		System.out.println("Verificando instancia de carro");

		boolean exist = false;

		for (int k = 0; k < cars.size(); k++) {
			if (cars.get(k).getId() == update.message().chat().id()) {
				System.out.println("Carro ja esta cadastrado com o ID: " + update.message().chat().id());
				exist = true;
			}
		}

		if (exist == false) {
			System.out.println("Seu carro agr esta cadastrado: " + update.message().chat().id());
			addCars(new Car(update.message().chat().id()));
		}

		for (Car car : cars) {
			System.out.println(car.getId());
		}
	}
}
