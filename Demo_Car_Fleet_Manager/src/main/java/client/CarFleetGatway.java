package client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarFleetGatway {

    RestTemplate restTemplate = new RestTemplate();
    private String serverUrl = "http://localhost:8081/carFleet";


    public  List<CarDTO> addCars() {
        CarDTO[] cars = {
                new CarDTO("DEF456", "Honda", "SUV", 35000),
                new CarDTO("GHI789", "Honda", "Hatchback", 200000),
                new CarDTO("JKL012", "Chevrolet", "Pickup", 40000),
                new CarDTO("MNO345", "BMW", "Coupe", 50000),
                new CarDTO("PQR678", "Toyota", "Sedan", 25000),
                new CarDTO("STU901", "Ford", "SUV", 45000),
                new CarDTO("VWX234", "Audi", "Convertible", 60000),
                new CarDTO("YZA567", "Mercedes-Benz", "Sedan", 75000),
                new CarDTO("BCD890", "Tesla", "SUV", 90000),
                new CarDTO("EFG123", "Lamborghini", "Coupe", 250000),
                new CarDTO("HIJ456", "Ferrari", "Convertible", 300000),
                new CarDTO("KLM789", "Porsche", "Sedan", 100000),
                new CarDTO("NOP012", "Bugatti", "Coupe", 500000)
        };

        RestTemplate restTemplate = new RestTemplate();
        List<CarDTO> addedCars = new ArrayList<>();

        for (CarDTO carDTO : cars) {
            CarDTO addedCar = restTemplate.postForObject(serverUrl + "/addCar", carDTO, CarDTO.class);
            addedCars.add(addedCar);
        }

        return addedCars;
    }
    public CarDTO searchForCar(String lcensePlate){
        CarDTO searchedCar = restTemplate.getForObject(serverUrl + "/searchCar/JKL012", CarDTO.class);
        return  searchedCar;

    }
    public void updateCar(String licensePlate , CarDTO carDTO){
        restTemplate.put(serverUrl + "/updateCar/" + licensePlate, carDTO);
        System.out.println("Car has been updated...");

    }
    public void deleteCar(String licencePlate){
        restTemplate.delete(serverUrl + "/removeCar/"+licencePlate);
        System.out.println("Car has been deleted...");

    }
    public ResponseEntity<?> getCarInfoIncludingNumberOfAvailablity(String brand) {
        String url = serverUrl + "/getCarInfoIncludingNumberOfAvailablity/" + brand;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return new ResponseEntity<String>("Available cars: " + response.getBody(), HttpStatus.OK);
        } catch (HttpStatusCodeException e) {
            CustomErrorType error = new CustomErrorType("Cars not available");
            return new ResponseEntity<CustomErrorType>(error, e.getStatusCode());
        }
    }



}
