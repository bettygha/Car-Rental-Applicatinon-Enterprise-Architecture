package car_rental_app.web;

import car_rental_app.domain.Customer;
import car_rental_app.service.CustomeException;
import car_rental_app.service.CustomerDTO;
import car_rental_app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/addNew")
    public ResponseEntity<?> addCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO savedCustomerDTO = customerService.addCustomer(customerDTO);
        return new ResponseEntity<CustomerDTO>(savedCustomerDTO, HttpStatus.OK);
    }

    @PutMapping("/updateCustomer/{customerNumber}")
    public ResponseEntity<?> updateCustomer(@PathVariable String customerNumber, @RequestBody CustomerDTO customerDTO) {
        CustomerDTO updatedCustomerDTO = customerService.updateCustomer(customerNumber, customerDTO);
        if (updatedCustomerDTO != null) {
            return new ResponseEntity<CustomerDTO>(updatedCustomerDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Customer with customer number "+ customerNumber + " is not available !"),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/removeCustomer/{customerNumber}")
    public ResponseEntity<?> removeCustomer(@PathVariable String customerNumber) {
        try{
            customerService.removeCustomer(customerNumber);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (CustomeException e){
            return new ResponseEntity<CustomErrorType>(new CustomErrorType(e.getErrorMessage()),HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/searchCustomer/{customerNumber}")
    public ResponseEntity<?> searchCustomer(@PathVariable String customerNumber) {
        CustomerDTO customerDTO = customerService.searchCustomer(customerNumber);
        if (customerDTO != null) {
            return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Customer with customer number "+ customerNumber + " is not available !"),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/reserveCarOrRentCar")
    public ResponseEntity<?> reserveCar(@RequestParam(value = "licensePlate") String licensePlate,@RequestParam(value = "customerNumber") String customerNumber, @RequestParam(value = "operation") String operation){

        if(operation.equals("reserveCar")){
            try{
               String message =  customerService.reserveCar(licensePlate,customerNumber);
                return new ResponseEntity<String>(message,HttpStatus.OK);
            }
            catch (CustomeException e){
                System.out.println(e.getErrorMessage());
                return new ResponseEntity<CustomErrorType>(new CustomErrorType(e.getErrorMessage()),HttpStatus.NOT_FOUND);
            }
        }
        try{
            String rentedCarString =  customerService.rentCar(licensePlate,customerNumber);
            return  new ResponseEntity<String>(rentedCarString,HttpStatus.OK);

        }
        catch (Exception e){
            System.out.println("Car not available....");
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Car Not Available "),HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping("/returnCar")
    public ResponseEntity<?> returnCar(@RequestParam(value = "licensePlate") String licensePlate,@RequestParam(value = "customerNumber") String customerNumber,@RequestParam(value = "price") String price){
        System.out.println("Returning....");
        try{
            Long priceValue = Long.parseLong(price);
            customerService.returnCar(licensePlate,customerNumber,priceValue);
            return  new ResponseEntity<String>(new String("Car has been returned"),HttpStatus.OK);
        }
        catch (CustomeException e){
            return  new ResponseEntity<CustomErrorType>(new CustomErrorType(e.getErrorMessage()),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getAllCustomerData/{customerNumber}")
    public ResponseEntity<?> getAllCustomerHistory(@PathVariable String customerNumber){
        CustomerDTO customer = customerService.getAllCustomerHistory(customerNumber);
        if(customer != null){
            return  new ResponseEntity<CustomerDTO>(customer,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("No customer with this Id"),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getAllDataFromCar/{licencePlate}")
    public ResponseEntity<?> getAllDataFromCar(@PathVariable String licencePlate){
        String getCarData = customerService.getAllDataFromCar(licencePlate);
        if(getCarData != null){
            return new ResponseEntity<String>(getCarData,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("No car with this licencePlate"),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getAllAvailableAndNotAvailableCars/{fleetId}")
    public ResponseEntity<?> getAllAvailableAndNotAvailableCars(){
        String cars = customerService.getAllAvailableAndNotAvailableCars();
        if(cars != null){
            return  new ResponseEntity<String>(cars,HttpStatus.OK);
        }
        System.out.println("Not Working ....");
        return new ResponseEntity<CustomErrorType>(new CustomErrorType("No cars  "),HttpStatus.NOT_FOUND);
    }
    @GetMapping("/getCarsWithBrand/{brand}")
    public ResponseEntity<?> searchCars(@PathVariable String brand){
        String cars = customerService.getCarsByBrand(brand);
        if(cars.length() > 0){
            return  new ResponseEntity<String>(cars,HttpStatus.OK);
        }
        System.out.println("Not Working ....");
        return new ResponseEntity<CustomErrorType>(new CustomErrorType("No available cars with this brand"),HttpStatus.NOT_FOUND);
    }
    @GetMapping("/findByCustomerNumber/{customerNumber}")
    public ResponseEntity<?> findByCustomerNumber(@PathVariable String customerNumber){
        Customer customer = customerService.findByCustomerNumber(customerNumber);
        if(customer != null){
            return new ResponseEntity<String>(customer.toString(),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("No Customer with this customer number"),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/findByCustomerEmail/{email}")
    public ResponseEntity<?> findByCustomerEmail(@PathVariable String email){
        Customer customer = customerService.findByCustomerEmail(email);
        if(customer != null){
            return new ResponseEntity<String>(customer.toString(),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("No Customer with this customer email"),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/findByCustomersByName/{name}")
    public ResponseEntity<?> findByCustomersByName(@PathVariable String name){
        String customers = customerService.findByCustomersByName(name);
        if(customers != null){
            return new ResponseEntity<String>(customers.toString(),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("No Customer with this customer name"),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/findByLevel/{level}")
    public ResponseEntity<?> findByLevel(@PathVariable String level){
        String customers = customerService.findByLevel(level);
        if(customers != null){
            return new ResponseEntity<String>(customers.toString(),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("No Customer with this customer level"),HttpStatus.NOT_FOUND);
        }
    }





    }

