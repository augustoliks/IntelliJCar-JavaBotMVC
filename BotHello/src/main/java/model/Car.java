package model;

public class Car {

	private long id;

	private String bat;
	private String gas;
	private String lat;
	private String lon;
	private String tsp;
	private String sal;
	private String dad;
	private String gsm;
	private String maps;

	public String getMaps() {
		return maps;
	}

	public void setMaps(String maps) {
		this.maps = maps;
	}

	public Car(long id) {
		super();
		this.id = id;
	}

	public String getSal() {
		return sal;
	}

	public String getTsp() {
		return tsp;
	}

	public void setTsp(String tsp) {
		this.tsp = tsp;
	}

	public void setSal(String sal) {
		this.sal = sal;
	}

	public String getDad() {
		return dad;
	}

	public void setDad(String dad) {
		this.dad = dad;
	}

	public String getGsm() {
		return gsm;
	}

	public void setGsm(String gsm) {
		this.gsm = gsm;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getBat() {
		return bat;
	}

	public void setBat(String bat) {
		this.bat = bat;
	}

	public String getGas() {
		return gas;
	}

	public void setGas(String gas) {
		this.gas = gas;
	}
}