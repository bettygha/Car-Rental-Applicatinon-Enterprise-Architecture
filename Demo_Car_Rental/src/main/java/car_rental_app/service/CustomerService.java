package car_rental_app.service;

import car_rental_app.Application;
import car_rental_app.dao.CustomerRepository;
import car_rental_app.dao.ReservedCarRepository;
import car_rental_app.domain.Customer;
import car_rental_app.domain.RentedCar;
import car_rental_app.domain.ReservedCar;
import car_rental_app.domain.ReturnedCar;
import car_rental_app.jms.ReservationMessageSender;
import car_rental_app.logging.CustomerServiceLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {


    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ReservationMessageSender reservationMessageSender;
    @Autowired
    private ReservedCarRepository reservedCarRepository;

    @Autowired
    private CustomerServiceLogger customerServiceLogger;

    ApplicationPropertiesConfiguration properties = new ApplicationPropertiesConfiguration();
    RestTemplate restTemplate = new RestTemplate();
    private String serverUrl = "http://localhost:8081/carFleet";


    public CustomerService() {
    }

    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        Customer customer = CustomerAdapter.getCustomerFromCustomerDTO(customerDTO);
        customerRepository.save(customer);
        customerServiceLogger.addCustomer();
        return customerDTO;
    }

    public CustomerDTO updateCustomer(String customerNumber, CustomerDTO customerDTO) {
        Customer customer = customerRepository.getById(customerNumber);
        if (customer != null) {
            customer = CustomerAdapter.getCustomerFromCustomerDTO(customerDTO);
            customerRepository.save(customer);
            customerServiceLogger.updateCustomer();
            return customerDTO;

        } else {
            System.out.println("Customer with customer number " + customerNumber + " is not available !");
            return null;
        }

    }

    public void removeCustomer(String customerNumber) {
        Optional<Customer> customer = customerRepository.findById(customerNumber);
        if (customer.isPresent()) {
            customerRepository.deleteById(customerNumber);
            customerServiceLogger.removeCustomer();
            System.out.println("Customer with customer number " + customerNumber + " has been removed !");
        } else {
            System.out.println("Customer with customer number " + customerNumber + " is not available !");
            throw new CustomeException("Customer with customer number " + customerNumber + " is not available !");

        }
    }

    public CustomerDTO searchCustomer(String customerNumber) {
        Optional<Customer> customer = customerRepository.findById(customerNumber);
        if (customer.isPresent()) {
            customerServiceLogger.searchCustomer();
            return CustomerAdapter.getCustomerDTOFromCustomer(customer.get());
        } else {
            return null;
        }
    }

    public String reserveCar(String licencePlate, String customerNumber) {
        Optional<Customer> targetCustomer = customerRepository.findById(customerNumber);
        if (targetCustomer.isPresent()) {
            List<Customer> customers = customerRepository.findAll();
            for (Customer customer : customers) {
                List<ReservedCar> reservedCarList = customer.getReservedCars();
                List<RentedCar> rentedCarList = customer.getRentedCars();
                if (reservedCarList != null) {
                    for (ReservedCar reservedCar : reservedCarList) {
                        if (reservedCar.getLicensePlate().equals(licencePlate)) {
                            System.out.println("Sorry the car you are looking for is already reserved !");
                            if (customer.getCustomerNumber().equals(customerNumber)) {

                                throw new CustomeException("Dear customer , Sorry the car you are looking for is already reserved by you ");
                            }
                            throw new CustomeException(" Sorry the car you are looking for is already reserved ");
                        }
                    }
                }
		  /* if (rentedCarList != null) {
				for (RentedCar rentedCar : rentedCarList) {
					if (rentedCar.getLicensePlate().equals(licencePlate)) {
						System.out.println("Sorry the car you are looking for is already reserved !");
						if(customer.getCustomerNumber().equals(customerNumber)){
							throw new CustomeException("Dear customer , Sorry the car you are looking for is already rented by you ");
						}
						throw new CustomeException(" Sorry the car you are looking for is already rented ");
					}
				}
			} */
            }
            if (targetCustomer.get().getReservedCars().size() > 2) {

                System.out.println("Cars reserved .." + targetCustomer.get().getReservedCars().size());
                throw new CustomeException("Sorry you have reached the maximum number of reservation !!!");
            } else {
                try {
                    System.out.println("Trying to reserve ...");
                    reservationMessageSender.sendReservationMessage(licencePlate);
                    ReservedCar reservedCar = new ReservedCar(licencePlate, new Date());
                    targetCustomer.get().reserveCar(reservedCar);
                    customerRepository.save(targetCustomer.get());
                    return new String("Thank You For Reserving....");

                } catch (Exception e) {
                    System.out.println("No car with this licencePlate !!!");
                    throw new CustomeException("No car with this licencePlate !!!");
                }
            }
        } else {
            throw new CustomeException("No customer with this customer number");
        }

    }

    public void returnCar(String licencePlate, String customerNumber, Long price) {
        Optional<Customer> customer = customerRepository.findById(customerNumber);

        if (customer.isPresent()) {

            ReturnedCar returnedCar = new ReturnedCar(licencePlate, new Date(), price);
            customer.get().returnCar(returnedCar);
            restTemplate.postForLocation(serverUrl + "/returnCar", new String(licencePlate));
            System.out.println("Car with licencePlate  is being returned-----------------");

            ///deleting from reserved car
            List<ReservedCar> reservedCarList = customer.get().getReservedCars();
            ReservedCar reservedCar = null;
            if (reservedCarList != null) {
                for (ReservedCar reservedCarI : reservedCarList) {
                    if (reservedCar.getLicensePlate().equals(licencePlate)) {
                        reservedCar = reservedCarI;
                    }
                }
                if (reservedCar != null) {
                    reservedCarList.remove(reservedCar);
                }
                customer.get().setReservedCars(reservedCarList);

                customerRepository.save(customer.get());
                System.out.println("Car has been returned.....");
            }
        }
    }

    public boolean checkCarIsInCustomersReservedList(String licencePlate, String customerNumber) {
        Optional<Customer> targetCustomer = customerRepository.findById(customerNumber);
        ReservedCar reservedCarToBeRemoved = null;

        if (targetCustomer.isPresent()) {
            List<Customer> customers = customerRepository.findAll();
            for (Customer customer : customers) {
                List<ReservedCar> reservedCarList = customer.getReservedCars();
                if (reservedCarList != null) {
                    for (ReservedCar reservedCar : reservedCarList) {
                        if (reservedCar.getLicensePlate().equals(licencePlate) && !(customer.getCustomerNumber().equals(customerNumber))) {
                            System.out.println("Sorry the car you are looking for is already reserved by some one else !");
                            return false;
                            //   throw new CustomeException(" Sorry the car you are looking for is already reserved by some one else !");
                        }
                        if (reservedCar.getLicensePlate().equals(licencePlate) && customer.getCustomerNumber().equals(customerNumber)) {
                            System.out.println("Sorry the car you are looking for is already reserved by you !");
                            reservedCarToBeRemoved = reservedCar;

                            //   throw new CustomeException(" Sorry the car you are looking for is already reserved by some one else !");
                        }
                    }
                }
            }
        /*    Customer customerToBeEdited = customerRepository.getById(customerNumber);
            List<ReservedCar> reservedCarsForCustomerToBeEdited = customerToBeEdited.getReservedCars();
            reservedCarsForCustomerToBeEdited.remove(reservedCarToBeRemoved);
            customerToBeEdited.setReservedCars(reservedCarsForCustomerToBeEdited);
            customerRepository.save(customerToBeEdited); */

        }
        System.out.println("All cars  reserved list doesn't have car "+ licencePlate);

        return true;
    }

    public boolean checkCarIsInCustomersRentedList(String licencePlate, String customerNumber) {
        Optional<Customer> targetCustomer = customerRepository.findById(customerNumber);
        if (targetCustomer.isPresent()) {
            List<Customer> customers = customerRepository.findAll();
            for (Customer customer : customers) {
                List<RentedCar> rentedCarList = customer.getRentedCars();
                if (rentedCarList != null) {
                    for (RentedCar rentedCar : rentedCarList) {
                        if (rentedCar.getLicensePlate().equals(licencePlate) && !(customer.getCustomerNumber().equals(customerNumber))) {
                            System.out.println("Sorry the car you are looking for is already rented  !");
                            return false;
                            //   throw new CustomeException(" Sorry the car you are looking for is already reserved by some one else !");
                        }
                    }
                }
            }
        }
        System.out.println("All cars rented list don't have "+ licencePlate);
        return true;
    }
    public boolean checkCarIsInItsOwnRentedList(String licencePlate, String customerNumber){
        Optional<Customer> customer = customerRepository.findById(customerNumber);{

            if(customer.isPresent()){
                List<RentedCar> rentedCarList = customer.get().getRentedCars();
                if(rentedCarList != null){
                    for(RentedCar rentedCar: rentedCarList){
                        if(rentedCar.getLicensePlate().equals(licencePlate)){
                            return false;
                        }
                    }
                }
            }
        }
        System.out.println("Customer rented list doesn't have car "+ licencePlate);

        return  true;
    }

    public String rentCar(String licencePlate, String customerNumber) {

        boolean checkerOne = checkCarIsInCustomersRentedList(licencePlate, customerNumber);
        boolean checkerTwo = checkCarIsInCustomersReservedList(licencePlate, customerNumber);
        boolean checkerThree = checkCarIsInItsOwnRentedList(licencePlate,customerNumber);

        Optional<Customer> customer = customerRepository.findById(customerNumber);
        int count = 0;
        int found = 0;
        if (customer.isPresent()) {
            if (checkerOne && checkerTwo) {
                if(checkerThree){
                    List<ReservedCar> reservedCars = customer.get().getReservedCars();
                    if (reservedCars.size() != 0) {
                        System.out.println("Iterating into the reserved cars ....");
                        ReservedCar reservedCarToBeRemoved;
                        for (ReservedCar reservedCar : reservedCars) {
                            if (reservedCar.getLicensePlate().equals(licencePlate)) {

                                reservedCarToBeRemoved = reservedCar;
                                found = count;
                            }
                            count++;
                        }

                        System.out.println("Car renting is being processed.....");
                        String rentedCarString = restTemplate.getForObject(serverUrl + "/rentCar/" + "{licencePlate}", String.class, licencePlate);
                        System.out.println("Car renting has been done being processed.....");
                        if (rentedCarString != null) {
                            System.out.println("Car with licencePlate  is  rented-----------------");


                            reservedCarToBeRemoved = reservedCars.get(found);
                            System.out.println("Here am i "+reservedCarToBeRemoved);
                            reservedCars.remove(reservedCarToBeRemoved);
                            customer.get().setReservedCars(reservedCars);
                            RentedCar rentedCar = new RentedCar(licencePlate,new Date());
                            customer.get().addRentedCar(rentedCar);
                            checkCustomerPointAndLevel(customerNumber);
                            customerRepository.save(customer.get());
                            reservedCarRepository.delete(reservedCarToBeRemoved);


                            System.out.println("Here am i ");
                            System.out.println("Car has been rented......");
                            System.out.println(rentedCarString);
                            return rentedCarString;
                        }
                    }
                    else {
                        System.out.println("Car renting is being processed.....");
                        String rentedCarString = restTemplate.getForObject(serverUrl + "/rentCar/" + "{licencePlate}", String.class, licencePlate);
                        System.out.println("Car renting has been done being processed.....");
                        if (rentedCarString != null) {
                            System.out.println("Car with licencePlate  is  rented-----------------");
                            RentedCar rentedCar = new RentedCar(licencePlate,new Date());
                            customer.get().addRentedCar(rentedCar);
                            checkCustomerPointAndLevel(customerNumber);
                            customerRepository.save(customer.get());
                            System.out.println("Car has been rented......");
                            System.out.println(rentedCarString);
                            return rentedCarString;
                        }

                    }
                }
                else {
                    System.out.println("Dear Customer , Car is already rented by you");
                    throw new CustomeException("Dear Customer , Car is already rented by you");
                }

            }

        }
        throw  new CustomeException("Sorry the car you are looking for is already rented ");



    }
    public void checkCustomerPointAndLevel(String customerNumber){
        Customer customer = customerRepository.getById(customerNumber);
        int point = customer.getPoint();

        if(point < 50 ){
            customer.setPoint(point+25);
            System.out.println("Point has been added to BRONZE level");
            if(!customer.getLevel().equals("BRONZE")){
                System.out.println("Customer Level has been changed to BRONZE");
                customer.setLevel("BRONZE");
            }
        } else if(point < 100){
            customer.setPoint(point+50);
            System.out.println("Point has been added to SILVER level");
            if(!customer.getLevel().equals("SILVER")){
                System.out.println("Customer Level has been changed to SILVER");
                customer.setLevel("SILVER");
            }
        } else {
            customer.setPoint(point+100);
            System.out.println("Point has been added to GOLD level");
            if(!customer.getLevel().equals("GOLD")){
                System.out.println("Customer Level has been changed to GOLD");
                customer.setLevel("GOLD");
            }
        }
    }

    public CustomerDTO getAllCustomerHistory(String customerNumber){
        Customer customer=customerRepository.getById(customerNumber);
        CustomerDTO customerDTO = CustomerAdapter.getCustomerDTOFromCustomer(customer);

        return customerDTO;
        }
public String getAllDataFromCar(String licensePlate){
        String carDate=restTemplate.getForObject(serverUrl+"/getCar/"+licensePlate,String.class);
        return carDate;
        }


@Scheduled(fixedRate = 20000)
public  String getAllAvailableAndNotAvailableCars(){
    String carsStatus=restTemplate.getForObject(serverUrl+"/getAllAvailableAndNotAvailableCars/1",String.class);
        System.out.println(carsStatus);
        return carsStatus;
        }



        public String getCarsByBrand(String brand){
        String cars=restTemplate.getForObject(serverUrl + "/getCarsWithBrand/" + "{brand}" ,String.class,brand);
    System.out.println(cars);
    return cars;

    }
    public Customer findByCustomerNumber(String customerNumber){
        Customer customer= customerRepository.findByCustomerNumber(customerNumber);
        return customer;
    }
    public Customer findByCustomerEmail(String email){
        Customer customer= customerRepository.findByEmail(email);
        return customer;
    }
    public String findByCustomersByName(String name){
        List<Customer> customers= customerRepository.findByName(name);
        customers.toString();
        String customersString = "";
        for(Customer customer1 : customers){
            customersString = "\n" + customersString + " \n "+ customer1.toString() + "\n";
        }
        System.out.println("CustomersDTO is "+ customers.toString());
        return customers.toString();
    }
    public String findByLevel(String level){
        List<Customer> customers= customerRepository.findByLevel(level);
        customers.toString();
        String customersString = "";
        for(Customer customer1 : customers){
            customersString = "\n" + customersString + " \n "+ customer1.toString() + "\n";
        }
        System.out.println("CustomersDTO is "+ customers.toString());
        return customers.toString();

    }


}