import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.pengrad.telegrambot.model.Update;

public class Model implements Subject {

	private List<Observer> observers = new LinkedList<Observer>();
	private List<Car> cars = new LinkedList<Car>();

	private static Model uniqueInstance;

	private String time	= "mostRecent";
		
	public void searchTime(Update update) {

		System.out.println("estou em time");		
		
		if (update.message().text().equals("now")) {
			this.time = "mostRecent";
		}
		else {
			this.time = update.message().text();
		}
		
		String data = "Results for the time: "+time;

		this.notifyObservers(update.message().chat().id(), data);
		System.out.println("Enviei isso: " + data + " -> " + update.message().chat().username());

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

		for (Car car : cars) {
			if (car.getId() == update.message().chat().id()) {
				data = car.getMaps();
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

	public void populityDatas(Update update) throws JsonSyntaxException, IOException {
		System.out.println("Atualizando os dados");

		Gson gson = new Gson();

		for (Car car : cars) {
			if (car.getId() == update.message().chat().id()) {

				cars.remove(car);

				car = gson.fromJson(new ConnectAPI().getJsonFromServer(this.time), Car.class);
				car.setId(update.message().chat().id());
				addCars(car);

			}
		}
		/*
		 * System.out.println("Todos os carros"); for (Car car : cars) {
		 * System.out.println("bat: " + car.getBat() + "\nGas: " + car.getGas() +
		 * "\nLat: " + car.getLat() + "\nLon: " + car.getLon()); }
		 */
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
