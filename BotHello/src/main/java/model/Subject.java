package model;

import view.Observer;

public interface Subject {
	
	public void registerObserver(Observer observer);
	public void notifyObservers(long chatId, String data);

}
