package car_fleet_app;


import car_fleet_app.domain.Car;
import car_fleet_app.domain.CarFleet;
import car_fleet_app.service.CarDTO;
import car_fleet_app.service.CarFleetService;
import car_fleet_app.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;


@SpringBootApplication
@EnableJms
public class SpringJmsReceiverApplication implements CommandLineRunner {

	@Autowired
	private CarFleetService carFleetService;
	@Autowired
	private CarService carService;

	public static void main(String[] args) {
		SpringApplication.run(SpringJmsReceiverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		carFleetService.createCarFleet(1L);
 /*
		CarDTO car1 = new CarDTO("ABC123", "Toyota", "Sedan", 25000);
		CarDTO car2 = new CarDTO("DEF456", "Honda", "SUV", 35000);
		CarDTO car3 = new CarDTO("GHI789", "Ford", "Hatchback", 200000);
		CarDTO car4 = new CarDTO("JKL012", "Chevrolet", "Pickup", 40000);
		CarDTO car5 = new CarDTO("MNO345", "BMW", "Coupe", 50000);

		carService.addCar(car1);
		carService.addCar(car2);
		carService.addCar(car3);
		carService.removeCar("GHI789");
		System.out.println(carService.updateCar("ABC123",car5));
		System.out.println(carService.searchCar("ABC123"));
*/


	}
}


