package car_rental_app.dao;

import car_rental_app.dao.CustomerRepository;
import car_rental_app.domain.Customer;
import car_rental_app.domain.RentedCar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testFindByEmail() {
        // Create a customer and save it using the test entity manager
        Customer customer = new Customer();
        customer.setCustomerNumber("123");
        customer.setName("John Doe");
        customer.setEmail("johndoe@example.com");

        entityManager.persist(customer);
        entityManager.flush();

        // Test the repository method
        Customer foundCustomer = customerRepository.findByEmail("johndoe@example.com");
        assertThat(foundCustomer.getEmail()).isEqualTo(customer.getEmail());
//        assertNotNull(foundCustomer);
//        assertEquals(customer.getEmail(), foundCustomer.getEmail());
    }

    @Test
    public void testFindByCustomerNumber() {
        // Create a customer and save it using the test entity manager
        Customer customer = new Customer();
        customer.setCustomerNumber("123");
        customer.setName("John Doe");
        customer.setEmail("johndoe@example.com");

        entityManager.persist(customer);
        entityManager.flush();

        // Test the repository method
        Customer foundCustomer = customerRepository.findByCustomerNumber("123");

        assertThat(foundCustomer.getCustomerNumber()).isEqualTo(customer.getCustomerNumber());
       // assertEquals(customer.getCustomerNumber(), foundCustomer.getCustomerNumber());
    }

    @Test
    public void testFindByName() {
        // Create two customers with the same name and save them using the test entity manager
        Customer customer1 = new Customer();
        customer1.setCustomerNumber("123");
        customer1.setName("John Doe");
        customer1.setEmail("johndoe@example.com");

        entityManager.persist(customer1);

        Customer customer2 = new Customer();
        customer2.setCustomerNumber("456");
        customer2.setName("John Doe");
        customer2.setEmail("janedoe@example.com");

        entityManager.persist(customer2);

        entityManager.flush();

        // Test the repository method
        List<Customer> foundCustomers = customerRepository.findByName("John Doe");
//
//        assertNotNull(foundCustomers);
//        assertEquals(2, foundCustomers.size());
        assertThat(foundCustomers.get(0).getCustomerNumber()).isEqualTo(customer1.getCustomerNumber());
    }

   /* @Test
    public void testFindRentedCarsByCustomerNumber() {
        Customer customer = new Customer();
        customer.setCustomerNumber("123");
        customer.setName("John Doe");
        customer.setEmail("johndoe@example.com");

        entityManager.persist(customer);

        RentedCar rentedCar1 = new RentedCar();
        rentedCar1.setLicensePlate("ABC123");
        rentedCar1.setRentedDate(new Date());

        entityManager.persist(rentedCar1);

        RentedCar rentedCar2 = new RentedCar();
        rentedCar2.setLicensePlate("DEF456");
        rentedCar2.setRentedDate(new Date());

        entityManager.persist(rentedCar2);

        customer.getRentedCars().add(rentedCar1);
        customer.getRentedCars().add(rentedCar2);

        entityManager.flush();

        List<RentedCar> foundRentedCars = customerRepository.findRentedCarsByCustomerNumber("123");

        assertThat(foundRentedCars).isNotNull();
        assertThat(foundRentedCars.size()).isEqualTo(2);
    } */

}

