package client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@EnableJms
public class FleetManagerApplication implements CommandLineRunner {

    @Autowired
    CarFleetGatway carFleetGatway;
    RestTemplate restTemplate = new RestTemplate();
    private String serverUrl = "http://localhost:8081/carFleet";


    public static void main(String[] args) {
        SpringApplication.run(FleetManagerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        // Add Cars
        System.out.println("############################### Adding Car #######################################################");
//
//        List<CarDTO> addedCars = carFleetGatway.addCars();
//        System.out.println("Cars added are :  ");
//        System.out.println("\n");
//        addedCars.stream().forEach(System.out::println);
//        System.out.println("\n");
        System.out.println("###################################################################################################");


//        // update car
//        System.out.println("################################## Updating Car with licencePlate NOP012 to Bugatti  Brand #####################################################");
//        CarDTO carDTO =new CarDTO("BCD890", "Bugatti", "Coupe", 500000);
//        carFleetGatway.updateCar("BCD890",carDTO);
//        System.out.println("###################################################################################################");
//
//        //search car
        System.out.println("######################################### Search Car with licencePlate NOP012 ##########################################################");
//
//        CarDTO carDTO1 =  carFleetGatway.searchForCar("BCD890");
//        System.out.println("Searched car .....  ");
//        System.out.println(carDTO1);
        System.out.println("###################################################################################################");


        //Get car information including the number of availablity

        System.out.println("##########################  Get car information including the number of availablity for brand 'Porsche'  ######################################################################################");

        ResponseEntity<?> response = carFleetGatway.getCarInfoIncludingNumberOfAvailablity("Porsche");
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println(response.getBody());
        } else {
            CustomErrorType error = (CustomErrorType) response.getBody();
            System.out.println("Error: " + error.getErrorMessage());
        }


        System.out.println("###################################################################################################");





    }
}
