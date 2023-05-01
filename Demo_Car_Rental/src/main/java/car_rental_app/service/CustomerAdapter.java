package car_rental_app.service;

import car_rental_app.domain.Customer;
import car_rental_app.domain.RentedCar;
import car_rental_app.domain.ReservedCar;
import car_rental_app.domain.ReturnedCar;

public class CustomerAdapter {

    public static CustomerDTO getCustomerDTOFromCustomer(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO(customer.getCustomerNumber(),customer.getName(),customer.getEmail());


        if(customer.getRentedCars() != null){
            for(RentedCar rentedCar : customer.getRentedCars()){
            customerDTO.addRentedCar(RentedCarAdapter.getRentedCarDTOFromRentedCar(rentedCar));
        }}
        if(customer.getReservedCars() !=null){
            for(ReservedCar reservedCar : customer.getReservedCars()){
                customerDTO.addReservedCar(ReservedCarAdapter.getReservedCarDtoFromReservedCar(reservedCar));
            }
        }
        if(customer.getReturnedCars() != null) {
            for(ReturnedCar returnedCar : customer.getReturnedCars()){
                customerDTO.addReturnedCars(ReturnedCarAdapter.getReturnedCarDTOFromReturnedCar(returnedCar));
            }
        }
        customerDTO.setLevel(customer.getLevel());
        customerDTO.setPoint(customer.getPoint());


        return  customerDTO;
    }
    public static Customer getCustomerFromCustomerDTO(CustomerDTO customerDTO){
        Customer customer = new Customer(customerDTO.getCustomerNumber(),customerDTO.getName(),customerDTO.getEmail());

        if(customerDTO.getRentedCars() != null){
            for(RentedCarDTO rentedCarDTO : customerDTO.getRentedCars()){
                customer.addRentedCar(RentedCarAdapter.getRentedCarFromRentedCarDTO(rentedCarDTO));
            }}
        if(customerDTO.getReservedCars() !=null){
            for(ReservedCarDTO reservedCarDTO : customerDTO.getReservedCars()){
                customer.addReservedCar(ReservedCarAdapter.getReservedCarDtoFromReservedCar(reservedCarDTO));
            }
        }
        if(customerDTO.getReturnedCars() != null) {
            for(ReturnedCarDTO returnedCarDTO : customerDTO.getReturnedCars()){
                customer.addReturnedCars(ReturnedCarAdapter.getReturnedCarFromReturnedCarDTO(returnedCarDTO));
            }
        }
        customer.setLevel(customerDTO.getLevel());
        customer.setPoint(customerDTO.getPoint());

        return  customer;
    }
}
