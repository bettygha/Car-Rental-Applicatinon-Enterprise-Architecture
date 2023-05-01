package car_fleet_app.service;

import car_fleet_app.domain.Car;

import java.util.ArrayList;
import java.util.List;

public class CarFleetDTO {
    List<CarDTO> allCars ;


    public CarFleetDTO(){
        allCars = new ArrayList<>();

    }

    public List<CarDTO> getAllCars() {
        return allCars;
    }

    public void addCarDTO(CarDTO carDTO) {
        this.allCars.add(carDTO);
    }
}
