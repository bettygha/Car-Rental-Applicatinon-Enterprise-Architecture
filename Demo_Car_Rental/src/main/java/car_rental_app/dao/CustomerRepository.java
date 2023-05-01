package car_rental_app.dao;

import car_rental_app.domain.Customer;
import car_rental_app.domain.RentedCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findByEmail(String email);
    Customer findByCustomerNumber(String customerNumber );
    List<Customer> findByName(String name);

    @Query("SELECT rentedCar FROM Customer c JOIN c.rentedCars rentedCar WHERE c.customerNumber = :customerNumber")
    List<RentedCar> findRentedCarsByCustomerNumber(@Param("customerNumber") String customerNumber);

    List<Customer> findByLevel(String level);

}



