package weatherNotification.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import weatherNotification.weather.Services.Weather;

@SpringBootApplication
public class WeatherApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(WeatherApplication.class, args);
		context.getBean(Weather.class).getWeatherData();
	}

}
