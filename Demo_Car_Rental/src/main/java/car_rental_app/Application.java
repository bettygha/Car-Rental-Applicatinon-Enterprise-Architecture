package car_rental_app;


import car_rental_app.service.ApplicationPropertiesConfiguration;
import car_rental_app.service.CustomerDTO;
import car_rental_app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableConfigurationProperties(ApplicationPropertiesConfiguration.class)
@EnableScheduling
public class Application {




	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


}
