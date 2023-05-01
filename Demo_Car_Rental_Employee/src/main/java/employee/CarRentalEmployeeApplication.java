package employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class CarRentalEmployeeApplication implements CommandLineRunner {
    RestTemplate restTemplate = new RestTemplate();
    @Autowired
    EmployeeGateway carRentalGatway;
    @Autowired
    CustomerGateway customerGatway;

    public static void main(String[] args) {
        SpringApplication.run(CarRentalEmployeeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("######################## Adding Customer .. with customer number 3 ###########################################################################");
        System.out.println("\n");
        // add customer
//       CustomerDTO customer1 = new CustomerDTO("3", "Jhone", "johndoe@example.com");
//        ResponseEntity<?> addCustomerResponse1 = carRentalGatway.addNewCustomer(customer1);
//        if (addCustomerResponse1.getStatusCode() == HttpStatus.OK) {
//            CustomerDTO savedCustomer = (CustomerDTO) addCustomerResponse1.getBody();
//            System.out.println("New customer added with " + savedCustomer );
//        } else {
//            CustomErrorType error = (CustomErrorType) addCustomerResponse1.getBody();
//            System.out.println("Error: " + error.getErrorMessage());
//        }

        System.out.println("\n");
        System.out.println("######################## Adding Customer .. with customer number 4 ###########################################################################");
        System.out.println("\n");

        // add customer
//       CustomerDTO customer2 = new CustomerDTO("4", "Emma Johnson", "emmajohnson@example.com");
//        ResponseEntity<?> addCustomerResponse2 = carRentalGatway.addNewCustomer(customer2);
//        if (addCustomerResponse2.getStatusCode() == HttpStatus.OK) {
//            CustomerDTO savedCustomer = (CustomerDTO) addCustomerResponse2.getBody();
//            System.out.println("New customer added with " + savedCustomer );
//        } else {
//            CustomErrorType error = (CustomErrorType) addCustomerResponse2.getBody();
//            System.out.println("Error: " + error.getErrorMessage());
//        }
        System.out.println("\n");
        System.out.println("######################## Adding Customer .. with customer number 5 ###########################################################################");
        System.out.println("\n");

        // add customer
//       CustomerDTO customer3 = new CustomerDTO("5", "Sarah Williams", "sarahwilliams@example.com");
//        ResponseEntity<?> response = carRentalGatway.addNewCustomer(customer3);
//        if (response.getStatusCode() == HttpStatus.OK) {
//            CustomerDTO savedCustomer = (CustomerDTO) response.getBody();
//            System.out.println("New customer added with " + savedCustomer );
//        } else {
//            CustomErrorType error = (CustomErrorType) response.getBody();
//            System.out.println("Error: " + error.getErrorMessage());
//        }
        System.out.println("\n");
        System.out.println("######################## Update customer .. with customer number 5 ###########################################################################");
        System.out.println("\n");

        // update customer
//         String customerNumber = "5";
//         CustomerDTO updatedCustomer = new CustomerDTO(customerNumber, "Michael", "michaelbrown@example.com");
//         carRentalGatway.updateCustomer(customerNumber, updatedCustomer);

        System.out.println("\n");
        System.out.println("######################## Search customer .. with customer number 5 ###########################################################################");
        System.out.println("\n");

        // search customer
//        CustomerDTO searchedCustomer = carRentalGatway.searchCustomer("5");
//        System.out.println("Searched Customer: " + searchedCustomer);

        System.out.println("\n");
        System.out.println("######################## Delete customer .. with customer number 5 ###########################################################################");
        System.out.println("\n");

        // delete customer
    //    carRentalGatway.deleteCustomer("5");

        System.out.println("\n");
        System.out.println("######################## Search car By brand  .. with brand 'TOYOTA' ###########################################################################");
        System.out.println("\n");

        //search car by brand
//        ResponseEntity<?> searchByCarBrandResponse = customerGatway.searchCar("Toyota");
//        if (searchByCarBrandResponse.getStatusCode() == HttpStatus.OK) {
//            System.out.println(searchByCarBrandResponse.getBody());
//        } else {
//            CustomErrorType error = (CustomErrorType) searchByCarBrandResponse.getBody();
//            System.out.println("Error: " + error.getErrorMessage());
//        }

        System.out.println("\n");
        System.out.println("######################## Reserve car  with licensePlate 'DEF456' for the 1st time  ###########################################################################");
        System.out.println("\n");

        //reserve car
//        ResponseEntity<?> reserveCarResponse = customerGatway.reserveOrRentCar("DEF456", "3", "reserveCar");
//        if (reserveCarResponse.getStatusCode() == HttpStatus.OK) {
//            System.out.println(reserveCarResponse.getBody());
//        } else {
//            CustomErrorType error = (CustomErrorType) reserveCarResponse.getBody();
//            System.out.println("Error: " + error.getErrorMessage());
//        }

        System.out.println("\n");
        System.out.println("######################## Trying to reserve again  with licensePlate 'GHI789'  for the 2nd time   ###########################################################################");
        System.out.println("\n");
//
//        ResponseEntity<?> reserveCarResponse1 = customerGatway.reserveOrRentCar("GHI789", "3", "reserveCar");
//        if (reserveCarResponse1.getStatusCode() == HttpStatus.OK) {
//            System.out.println(reserveCarResponse1.getBody());
//        } else {
//            CustomErrorType error = (CustomErrorType) reserveCarResponse1.getBody();
//            System.out.println("Error: " + error.getErrorMessage());
//        }


        System.out.println("\n");
        System.out.println("######################## Trying to reserve again  with licensePlate 'JKL012' for the 3rd  time ###########################################################################");
        System.out.println("\n");
//
//       ResponseEntity<?> reserveCarResponse2 = customerGatway.reserveOrRentCar("JKL012", "3", "reserveCar");
//        if (reserveCarResponse2.getStatusCode() == HttpStatus.OK) {
//            System.out.println(reserveCarResponse2.getBody());
//        } else {
//            CustomErrorType error = (CustomErrorType) reserveCarResponse2.getBody();
//            System.out.println("Error: " + error.getErrorMessage());
//        }


        System.out.println("\n");
        System.out.println("######################## Trying to reserve again  with licensePlate 'MNO345' for the 4thh time ###########################################################################");
        System.out.println("\n");
//
//        ResponseEntity<?> reserveCarResponse3 = customerGatway.reserveOrRentCar("MNO345", "3", "reserveCar");
//        if (reserveCarResponse3.getStatusCode() == HttpStatus.OK) {
//            System.out.println(reserveCarResponse3.getBody());
//        } else {
//            CustomErrorType error = (CustomErrorType) reserveCarResponse3.getBody();
//            System.out.println("Error: " + error.getErrorMessage());
//        }


        System.out.println("\n");
        System.out.println("######################## Renting car  with licensePlate 'DEF456' ###########################################################################");
        System.out.println("\n");

        //renting car
//       ResponseEntity<?> rentCarResponse = customerGatway.reserveOrRentCar("DEF456", "3", "rentCar");
//        if (rentCarResponse.getStatusCode() == HttpStatus.OK) {
//            System.out.println(rentCarResponse.getBody());
//        } else {
//            CustomErrorType error = (CustomErrorType) rentCarResponse.getBody();
//            System.out.println("Error: " + error.getErrorMessage());
//        }
//        System.out.println("\n");
//        System.out.println("######################## Renting car  with licensePlate 'NOP012' for customer 4 ###########################################################################");
//        System.out.println("\n");

        //renting car for customer 4
//        ResponseEntity<?> rentCarResponseCustomer4 = customerGatway.reserveOrRentCar("NOP012", "4", "rentCar");
//        if (rentCarResponseCustomer4.getStatusCode() == HttpStatus.OK) {
//            System.out.println(rentCarResponseCustomer4.getBody());
//        } else {
//            CustomErrorType error = (CustomErrorType) rentCarResponseCustomer4.getBody();
//            System.out.println("Error: " + error.getErrorMessage());
//        }

        //renting car for customer 4 again with the same licencePlate
//        ResponseEntity<?> rentCarResponseCustomer4Again = customerGatway.reserveOrRentCar("DEF456", "4", "rentCar");
//        if (rentCarResponseCustomer4Again.getStatusCode() == HttpStatus.OK) {
//            System.out.println(rentCarResponseCustomer4Again.getBody());
//        } else {
//            CustomErrorType error = (CustomErrorType) rentCarResponseCustomer4Again.getBody();
//            System.out.println("Error: " + error.getErrorMessage());
//        }


        System.out.println("\n");
        System.out.println("######################## Renting car  with licensePlate 'VWX234' ###########################################################################");
        System.out.println("\n");

        //renting car
//        ResponseEntity<?> rentCarResponse2 = customerGatway.reserveOrRentCar("VWX234", "3", "rentCar");
//        if (rentCarResponse2.getStatusCode() == HttpStatus.OK) {
//            System.out.println(rentCarResponse2.getBody());
//        } else {
//            CustomErrorType error = (CustomErrorType) rentCarResponse2.getBody();
//            System.out.println("Error: " + error.getErrorMessage());
//        }
//
//        System.out.println("\n");
//        System.out.println("######################## Renting car  with licensePlate 'YZA567' ###########################################################################");
//        System.out.println("\n");

        //renting car
//        ResponseEntity<?> rentCarResponse3 = customerGatway.reserveOrRentCar("YZA567", "3", "rentCar");
//        if (rentCarResponse3.getStatusCode() == HttpStatus.OK) {
//            System.out.println(rentCarResponse3.getBody());
//        } else {
//            CustomErrorType error = (CustomErrorType) rentCarResponse3.getBody();
//            System.out.println("Error: " + error.getErrorMessage());
//        }
//
//        System.out.println("\n");
//        System.out.println("######################## Returning car  with licensePlate 'YZA567' ###########################################################################");
//        System.out.println("\n");

        //return car
        //customerGatway.returnCar("YZA567", "3", "1000");

        System.out.println("\n");
        System.out.println("######################## Find Customer By customerNumber  3 ###########################################################################");
        System.out.println("\n");

        //find customer by number
       // carRentalGatway.findCustomerByNumber("3");

        System.out.println("\n");
        System.out.println("######################## Find Customer By name  Jhone ###########################################################################");
        System.out.println("\n");

        // find by name
      //  carRentalGatway.findByCustomersByName("Jhone");

        System.out.println("\n");
        System.out.println("######################## Find Customer By email  johndoe@example.com ###########################################################################");
        System.out.println("\n");

        //find by email
     //   carRentalGatway.findByCustomerEmail("johndoe@example.com");

        System.out.println("\n");
        System.out.println("######################## Get all Customer history by customerNumber ###########################################################################");
        System.out.println("\n");

        //getting all customer history
    //    carRentalGatway.getAllCustomerHistory("3");

        System.out.println("\n");
        System.out.println("######################## Get all data from a articular Car by licencePlate  DEF456 ###########################################################################");
        System.out.println("\n");

        //getting data from a particulat car
      //  carRentalGatway.getAllDataFromCar("DEF456");

        System.out.println("\n");
        System.out.println("######################## Get all all available and not available car ###########################################################################");
        System.out.println("\n");

        //getting all available and not available car
     //  carRentalGatway.getAllAvailableAndNotAvailableCars("3");

        //finding customer by level
        System.out.println("\n");
        System.out.println("######################## Finding customer by level ###########################################################################");
      //  carRentalGatway.findByLevel("SILVER");
        System.out.println("\n");



    }
}
