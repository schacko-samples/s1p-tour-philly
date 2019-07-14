package s1p.tour.philly.sensorsource;

public class EnrichedSensor {

	private int id;
	private double temperatureInCelsius;

	private int userId;

	public EnrichedSensor() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTemperatureInCelsius() {
		return temperatureInCelsius;
	}

	public void setTemperatureInCelsius(double temperatureInCelsius) {
		this.temperatureInCelsius = temperatureInCelsius;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
