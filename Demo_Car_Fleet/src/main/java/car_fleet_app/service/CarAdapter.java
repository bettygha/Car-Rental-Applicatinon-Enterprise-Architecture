package car_fleet_app.service;

import car_fleet_app.domain.Car;

public class CarAdapter {

    public static CarDTO getCarDTOFromCar(Car car){
        CarDTO carDTO = new CarDTO(car.getLicencePlate(),car.getBrand(),car.getType(),car.getPrice());
        return  carDTO;

    }
    public static  Car getCarFromCarDTO(CarDTO carDTO){
        Car car = new Car(carDTO.getLicencePlate(),carDTO.getBrand(),carDTO.getType(),carDTO.getPrice());
        return car;

    }
}
