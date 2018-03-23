import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.pengrad.telegrambot.model.Update;

public class Model implements Subject {

	private List<Observer> observers = new LinkedList<Observer>();
	private List<Car> Cars = new LinkedList<Car>();

	private static Model uniqueInstance;

	public static Model getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Model();
		}
		return uniqueInstance;
	}

	public void addCars(Car car) {
		this.Cars.add(car);
	}

	public void searchBattery(Update update) throws JsonSyntaxException, IOException {
		populityDatas(update);

		System.out.println("Estou em Bateria");

		String data = null;
		for (Car car : Cars) {

			if (car.getId() == update.message().chat().id()) {
				data = car.getBat();
			}
		}

		if (data != null) {
			this.notifyObservers(update.message().chat().id(), data);
		} else {
			this.notifyObservers(update.message().chat().id(), "NOT FOUND");
		}
	}

	public void searchTemperature(Update update) throws JsonSyntaxException, IOException {
		this.populityDatas(update);

		System.out.println("Estou em Temperatura");
		String data = null;
		for (Car car : Cars) {

			if (car.getId() == update.message().chat().id()) {
				data = car.getLat();
			}
		}

		if (data != null) {
			this.notifyObservers(update.message().chat().id(), data);
		} else {
			this.notifyObservers(update.message().chat().id(), "NOT FOUND");
		}

	}

	public void searchGPS(Update update) throws JsonSyntaxException, IOException {
		this.populityDatas(update);

		System.out.println("Estou em GPS");
		String data = null;
		for (Car car : Cars) {

			if (car.getId() == update.message().chat().id()) {
				data = car.getLon();
			}
		}

		if (data != null) {
			this.notifyObservers(update.message().chat().id(), data);
		} else {
			this.notifyObservers(update.message().chat().id(), "NOT FOUND");
		}

	}

	public void searchGas(Update update) throws JsonSyntaxException, IOException {
		this.populityDatas(update);

		System.out.println("Estou em Gasolina");

		
		String data = null;

		for (Car car : Cars) {

			if (car.getId() == update.message().chat().id()) {
				data = car.getGas();
			}
		}

		if (data != null) {
			this.notifyObservers(update.message().chat().id(), data);

		} else {
			this.notifyObservers(update.message().chat().id(), "NOT FOUND");
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

		for (Car car : Cars) {
			if (car.getId() == update.message().chat().id()) {
				car = gson.fromJson(new GetJson().getJsonFromServer(), Car.class);
			}
		}
		gson.
		for (Car car : Cars) {
			System.out.println("bat: " + car.getBat() + "\nGas: " + car.getGas() + "\nLat: " + car.getLat() + "\nLon: "
					+ car.getLon());
		}

	}

	public void registerCar(Update update) {
		System.out.println("Verificando instancia de carro");

		boolean exist = false;

		for (int k = 0; k < Cars.size(); k++) {
			if (Cars.get(k).getId() == update.message().chat().id()) {
				System.out.println("Carro ja esta cadastrado com o ID: " + update.message().chat().id());
				exist = true;
			}
		}

		if (exist == false) {
			System.out.println("Seu carro agr esta cadastrado: " + update.message().chat().id());
			addCars(new Car(update.message().chat().id()));
		}

		for (Car car : Cars) {
			System.out.println(car.getId());
		}
	}
}
