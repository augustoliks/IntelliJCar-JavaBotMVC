public class Car {

	private long id;
	private String lat;
	private String lon;
	private String bat;
	private String gas;
	
	public Car(long id) {
		super();
		this.id = id;
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