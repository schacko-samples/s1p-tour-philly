package s1p.tour.philly.sensorprocessor;

import java.util.Random;
import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import s1p.tour.philly.sensorsource.EnrichedSensor;
import s1p.tour.philly.sensorsource.Sensor;

@SpringBootApplication
public class SensorProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensorProcessorApplication.class, args);
	}

	@Bean
	public Function<Sensor, EnrichedSensor> enrichedSensor() {
		return sensor -> {
			EnrichedSensor enrichedSensor = new EnrichedSensor();
			enrichedSensor.setId(sensor.getId());
			enrichedSensor.setTemperatureInCelsius( (sensor.getTemperature() - 32) * (5.0/9.0) );
			enrichedSensor.setUserId(new Random().nextInt(1000));
			return enrichedSensor;
		};
	}

}
