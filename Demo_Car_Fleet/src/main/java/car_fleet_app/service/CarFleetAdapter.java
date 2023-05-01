package car_fleet_app.service;

import car_fleet_app.domain.Car;
import car_fleet_app.domain.CarFleet;

import java.util.ArrayList;
import java.util.List;

public class CarFleetAdapter {
    public static CarFleetDTO getCarFleetToCarFleetDTO(CarFleet carFleet){
        CarFleetDTO carFleetDTO = new CarFleetDTO();

        for(Car car : carFleet.getAllCars()){
            carFleetDTO.addCarDTO(CarAdapter.getCarDTOFromCar(car));
        }
        return carFleetDTO;
    }
    public static CarFleet getCarFleetDTOFromCarFleet(CarFleetDTO carFleetDTO){
        CarFleet carFleet = new CarFleet(1L);

        for (CarDTO carDTO : carFleetDTO.getAllCars()){
            carFleet.addCar(CarAdapter.getCarFromCarDTO(carDTO));
        }
        return  carFleet;
    }
}
