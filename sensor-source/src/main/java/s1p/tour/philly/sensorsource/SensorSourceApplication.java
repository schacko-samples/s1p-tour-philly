package s1p.tour.philly.sensorsource;

import java.util.Random;
import java.util.function.Supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class SensorSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensorSourceApplication.class, args);
	}

	@Bean
	@Scheduled(fixedRate = 1000L)
	public Supplier<Sensor> sensorInfo() {

		return () -> {
			Sensor sensor = new Sensor();
			sensor.setId(new Random().nextInt(5));
			sensor.setTemperature(new Random().nextInt(100));
			return sensor;
		};
	}

}
