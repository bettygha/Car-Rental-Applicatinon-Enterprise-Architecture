package car_fleet_app.service;

import car_fleet_app.dao.CarFleetRepository;
import car_fleet_app.dao.CarRepository;
import car_fleet_app.domain.Car;
import car_fleet_app.domain.CarFleet;
import car_fleet_app.jms.CarQuantityMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackages = "")
public class CarService {


    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarFleetRepository carFleetRepository;
    @Autowired
    private CarQuantityMessageSender carQuantityMessageSender;



    @JmsListener(destination = "me")
    public void pickUp(final String licencePlate){
        System.out.println(licencePlate + " yess");

    }
    public void addCar(CarDTO carDTO){
        Car newCar= CarAdapter.getCarFromCarDTO(carDTO);
        CarFleet carFleet = carFleetRepository.findById(1L).get();
        carFleet.addCar(newCar);
        carFleetRepository.save(carFleet);
    }
    public void removeCar(String licencePlate){
        CarFleet carFleet = carFleetRepository.findById(1L).get();
        carFleet.removeCar(licencePlate);
        carFleetRepository.save(carFleet);
    }
    public CarDTO updateCar(String licencePlate , CarDTO carDTO){
        CarFleet carFleet = carFleetRepository.findById(1L).get();
        Car car = carFleet.updateCar(licencePlate,CarAdapter.getCarFromCarDTO(carDTO));
        carFleetRepository.save(carFleet);
        CarDTO carDTOToBeReturned = CarAdapter.getCarDTOFromCar(car);
        return  carDTOToBeReturned;
    }
    public CarDTO searchCar(String licencePlate) {
        CarFleet carFleet = carFleetRepository.findById(1L).get();
        Car car = carFleet.searchCar(licencePlate);
        if (car == null) {
            System.out.println("Car with license plate " + licencePlate + " is not available.");
            return null;
        }
        CarDTO carDTO = CarAdapter.getCarDTOFromCar(car);
        return carDTO;
    }
    public CarDTO reserveCar(String licencePlate) {
        CarFleet carFleet = carFleetRepository.findById(1L).get();
        try {
            Car car = carFleet.reserveCar(licencePlate);
            carFleetRepository.save(carFleet);
            checkQuantityOfCar(car.getBrand());
            CarDTO carDTO = CarAdapter.getCarDTOFromCar(car);
            return carDTO;
        } catch (NullPointerException e) {
            System.out.println("Car with license plate " + licencePlate + " is not available.");
            return  null;

        }
    }
    public void returnCar(String licencePlate){
        CarFleet carFleet = carFleetRepository.findById(1L).get();
        try {
            Car car = carFleet.returnCar(licencePlate);
            carFleetRepository.save(carFleet);
        }
        catch (NullPointerException e){
            System.out.println("Car with license plate " + licencePlate + " is not rented.");
        }

    }
    public CarDTO rentCar(String licencePlate){
        CarFleet carFleet = carFleetRepository.findById(1L).get();
        try{
            Car car = carFleet.rentCar(licencePlate);
            carFleetRepository.save(carFleet);
            checkQuantityOfCar(car.getBrand());
            CarDTO carDTO = CarAdapter.getCarDTOFromCar(car);
            return  carDTO;
        }
        catch (NullPointerException e){
            System.out.println("Car with license plate " + licencePlate + " is not available.");
            return null;
        }
    }
    public void checkQuantityOfCar(String brand){
        int count = 0;
        Optional<CarFleet> carFleet = carFleetRepository.findById(1L);
        List<Car> carList = carFleet.get().getAllCars();
        for(Car car : carList){
            if(car.getBrand().equals(brand) && car.isAvailable() == true){
                count++;
            }
        }
        if(count < 3){
            String message = "Car with brand "+ brand + " quantity is less than 3 , Please add car !!!";
            carQuantityMessageSender.sendCarQuantityMessage(message);
        }

    }
    public String getAllAvailableCars(){
        Optional<CarFleet> carFleet = carFleetRepository.findById(1L);
        List<Car> carList = carFleet.get().getAllCars();
        String cars = " ";
        for(Car car: carList){
            if(car.isAvailable() == true){
                cars= " "+cars + " " + car.toString() + "\n";
            }
        }
        return  cars;

    }
    public String getAllNotAvailableCars(){
        Optional<CarFleet> carFleet = carFleetRepository.findById(1L);
        List<Car> carList = carFleet.get().getAllCars();
        String cars = " ";
        for(Car car: carList){
            if(car.isAvailable() == false){
                cars= " "+cars + " " + car.toString() + "\n";
            }
        }
        return  cars;

    }
public String getCarsWithBrand(String brand){
        CarFleet carFleet = carFleetRepository.findAll().get(0);
        String cars = "";
        for(Car car : carFleet.getAllCars()){
            if( car.isAvailable()== true && car.getBrand().equals(brand)){
                cars =  cars + " "+ car + "\n";
            }
        }
        return  cars;




    }
    public String getCarInfoIncludingNumberOfAvailablity(String brand){
        String availableCars = getCarsWithBrand(brand);
        CarFleet carFleet = carFleetRepository.findAll().get(0);
        int count = 0;
        for(Car car : carFleet.getAllCars()){
            if( car.isAvailable()== true && car.getBrand().equals(brand)){
                count++;
            }
        }
        String returnData = "Available number of brand: "+ brand + " is " + String.valueOf(count) + "\n"+availableCars;
        return  returnData;

    }
}
