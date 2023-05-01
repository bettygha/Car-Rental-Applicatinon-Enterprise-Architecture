package car_rental_app.domain;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer("1234", "John Doe", "johndoe@example.com");
    }

    @Test
    public void testConstructor() {
        assertEquals("1234", customer.getCustomerNumber());
        assertEquals("John Doe", customer.getName());
        assertEquals("johndoe@example.com", customer.getEmail());
        assertEquals(new ArrayList<RentedCar>(), customer.getRentedCars());
        assertEquals(new ArrayList<ReservedCar>(), customer.getReservedCars());
        assertEquals(new ArrayList<ReturnedCar>(), customer.getReturnedCars());
        assertEquals(0, customer.getPoint());
        assertEquals("Starter", customer.getLevel());
    }

    @Test
    public void testAddRentedCar() {
        RentedCar rentedCar = new RentedCar();
        customer.addRentedCar(rentedCar);
        List<RentedCar> rentedCars = customer.getRentedCars();
        assertEquals(1, rentedCars.size());
        assertEquals(rentedCar, rentedCars.get(0));
    }

    @Test
    public void testAddReservedCar() {
        ReservedCar reservedCar = new ReservedCar();
        customer.addReservedCar(reservedCar);
        List<ReservedCar> reservedCars = customer.getReservedCars();
        assertEquals(1, reservedCars.size());
        assertEquals(reservedCar, reservedCars.get(0));
    }

    @Test
    public void testAddReturnedCar() {
        ReturnedCar returnedCar = new ReturnedCar();
        customer.addReturnedCars(returnedCar);
        List<ReturnedCar> returnedCars = customer.getReturnedCars();
        assertEquals(1, returnedCars.size());
        assertEquals(returnedCar, returnedCars.get(0));
    }

    @Test
    public void testReserveCar() {
        ReservedCar reservedCar = new ReservedCar();
        customer.reserveCar(reservedCar);
        List<ReservedCar> reservedCars = customer.getReservedCars();
        assertEquals(1, reservedCars.size());
        assertEquals(reservedCar, reservedCars.get(0));
    }

    @Test
    public void testReturnCar() {
        ReturnedCar returnedCar = new ReturnedCar();
        customer.returnCar(returnedCar);
        List<ReturnedCar> returnedCars = customer.getReturnedCars();
        assertEquals(1, returnedCars.size());
        assertEquals(returnedCar, returnedCars.get(0));
    }

    @Test
    public void testRentCar() {
        RentedCar rentedCar = new RentedCar();
        customer.rentCar(rentedCar);
        List<RentedCar> rentedCars = customer.getRentedCars();
        assertEquals(1, rentedCars.size());
        assertEquals(rentedCar, rentedCars.get(0));
    }}


