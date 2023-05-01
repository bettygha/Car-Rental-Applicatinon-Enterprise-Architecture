package car_fleet_app.domain;


import car_fleet_app.service.CarNotFoundException;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;
import javax.persistence.*;
@Document
public class CarFleet {
    @Id
    private long id ;
    private List<Car> allCars ;
    public CarFleet(Long id){
        this.id = id;
        allCars = new ArrayList<>();
    }
    public List<Car> getAllCars() {
        return allCars;
    }
    public void addCar(Car car) {
        this.allCars.add(car);
    }
    public void removeCar(String licencePlate){
        boolean carRemoved = false;
        for (Car car : allCars) {
            if (car.getLicencePlate().equals(licencePlate)) {
                allCars.remove(car);
                carRemoved = true;
                break;
            }
        }
        if (carRemoved) {
            System.out.println("Car with license plate " + licencePlate + " has been removed.");
        } else {
            throw new CarNotFoundException("Car with license plate " + licencePlate + " is not available.");
        }}
    public Car updateCar(String licencePlate,Car car){
        for (Car carToUpdate : allCars) {
            if (carToUpdate.getLicencePlate().equals(licencePlate)) {
                carToUpdate.setBrand(car.getBrand());
                carToUpdate.setType(car.getType());
                carToUpdate.setPrice(car.getPrice());
                System.out.println("Car with license plate " + licencePlate + " has been updated.");
                return carToUpdate;
            }
        }
        System.out.println("Car with license plate " + licencePlate + " is not available.");
        return null;

    }
    public Car searchCar(String licencePlate) {
        for(Car car : allCars){
            if(car.getLicencePlate().equals(licencePlate)){
                return car;
            }
        }

        throw new NullPointerException("Car with license plate " + licencePlate + " is not available.");
    }
    public Car reserveCar(String licencePlate){
        Car car = searchCar(licencePlate);
        if (car != null) {
            car.setAvailable(false);
            System.out.println("Car with licencePlate "+ licencePlate+ " is reserved in domain");
            return car;
        }
        throw new NullPointerException("Car with license plate " + licencePlate + " is not available.");
    }
    public Car rentCar(String licencePlate){
        Car car = searchCar(licencePlate);
        if (car != null) {
            car.setAvailable(false);
            System.out.println("Car with licencePlate "+ licencePlate+ " is rented");
            return car;
        }
        throw new NullPointerException("Car with license plate " + licencePlate + " is not available.");
    }
    public Car returnCar(String licencePlate){
        try{
            int index = 0;
            for(int i= 0 ; i < allCars.size() ; i++){
                if(allCars.get(i).getLicencePlate().equals(licencePlate)){
                    index = i;
                }
            }
            Car carFound = allCars.get(index);
            carFound.setAvailable(true);
            System.out.println("Car with licencePlate "+ licencePlate+ " is returned");
            return carFound;
        }
        catch (Exception e){
            throw new NullPointerException("Car with license plate " + licencePlate + " is not available.");

        }


    }





}
