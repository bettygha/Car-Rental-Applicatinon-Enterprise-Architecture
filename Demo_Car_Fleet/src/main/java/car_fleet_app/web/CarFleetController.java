package car_fleet_app.web;

import car_fleet_app.domain.Car;
import car_fleet_app.service.CarDTO;
import car_fleet_app.service.CarNotFoundException;
import car_fleet_app.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarFleetController {

    @Autowired
    private CarService carService;


    @PostMapping("/carFleet/addCar")
    public ResponseEntity<?> addNewCar(@RequestBody CarDTO carDTO){
        carService.addCar(carDTO);
        carDTO.setAvailable(true);
        return  new ResponseEntity<CarDTO>(carDTO, HttpStatus.OK);
    }

    @DeleteMapping("/carFleet/removeCar/{licencePlate}")
    public ResponseEntity<?> removeCar(@PathVariable String licencePlate){
        try {
            carService.removeCar(licencePlate);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CarNotFoundException e) {
            return new ResponseEntity<>(new CustomErrorType(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/carFleet/updateCar/{licencePlate}")
    public ResponseEntity<?> updateCar(@PathVariable String licencePlate, @RequestBody CarDTO carDTO){
        try {
            CarDTO updatedCar = carService.updateCar(licencePlate, carDTO);
            if (updatedCar != null) {
                return new ResponseEntity<CarDTO>(updatedCar, HttpStatus.OK);
            } else {
                return new ResponseEntity<CustomErrorType>(new CustomErrorType("Car not available"), HttpStatus.NOT_FOUND);
            }
        } catch (CarNotFoundException e) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/carFleet/searchCar/{licencePlate}")
    public ResponseEntity<?> searchCar(@PathVariable String licencePlate){
       CarDTO carDTO = carService.searchCar(licencePlate);
       if(carDTO != null){
           return new ResponseEntity<CarDTO>(carDTO,HttpStatus.OK);
       }
       else{
           return new ResponseEntity<CustomErrorType>(new CustomErrorType("Car not available"),HttpStatus.NOT_FOUND);
       }
    }
    @GetMapping("/carFleet/rentCar/{licencePlate}")
    public ResponseEntity<?> rentCar(@PathVariable String licencePlate ) {
        CarDTO carDTOToReturn = carService.searchCar(licencePlate);

            CarDTO carDTO = carService.rentCar(licencePlate);
            if(carDTO == null){
                String nullString = null;
                return new ResponseEntity<String>(nullString,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<String>(carDTOToReturn.toString(), HttpStatus.OK);
    }
    @PostMapping("/carFleet/returnCar")
    public ResponseEntity<?> returnCar(@RequestBody String licencePlate) {
        carService.returnCar(licencePlate);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    @GetMapping("/carFleet/getCar/{licencePlate}")
    public ResponseEntity<?> getCar(@PathVariable String licencePlate){
        CarDTO carDTO = carService.searchCar(licencePlate);
        String carTOReturn = carDTO.getLicencePlate() + " " + carDTO.getBrand() + " "+ carDTO.getType()+" "+ carDTO.getPrice();
        if(carDTO != null){
            return new ResponseEntity<String>(carTOReturn,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Car not available"),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/carFleet/getAllAvailableAndNotAvailableCars/{carStatus}")
    public ResponseEntity<?> getAllAvailableAndNotAvailableCars(){
        try {
            String availableCars = carService.getAllAvailableCars();
            String notAvailableCars = carService.getAllNotAvailableCars();
            return  new ResponseEntity<String>("Available cars : "+ availableCars + "\n Not Available cars: "+notAvailableCars ,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Car not available"),HttpStatus.NOT_FOUND);

        }

    }
    @GetMapping("/carFleet/getCarsWithBrand/{brand}")
    public ResponseEntity<?> getCarsWithBrand(@PathVariable String brand){
        try {
            String availableCarsWithSpecificBrand = carService.getCarsWithBrand(brand);
            return  new ResponseEntity<String>("Available cars : "+ availableCarsWithSpecificBrand ,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Cars not available"),HttpStatus.NOT_FOUND);

        }

    }
    @GetMapping("/carFleet/getCarInfoIncludingNumberOfAvailablity/{brand}")
    public ResponseEntity<?> getCarInfoIncludingNumberOfAvailablity(@PathVariable String brand){
        try {
            String availableCarsWithSpecificBrand = carService.getCarInfoIncludingNumberOfAvailablity(brand);
            return  new ResponseEntity<String>("Available cars : "+ availableCarsWithSpecificBrand ,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Cars not available"),HttpStatus.NOT_FOUND);

        }

    }
}
