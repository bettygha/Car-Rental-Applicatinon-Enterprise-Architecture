package car_fleet_app.domain;

import static org.junit.jupiter.api.Assertions.*;

import car_fleet_app.service.CarNotFoundException;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CarFleetTest {

    private CarFleet carFleet;

    @BeforeEach
    public void setUp() {
        carFleet = new CarFleet(1L);
    }

    @Test
    public void testAddCar() {
        Car car = new Car("AB-1234", "Toyota", "Corolla", 100);
        car.setAvailable(true);
        carFleet.addCar(car);
        assertTrue(carFleet.getAllCars().contains(car));
    }

    @Test
    public void testRemoveCar() {
        Car car = new Car("AB-1234", "Toyota", "Corolla", 100);
        car.setAvailable(true);

        carFleet.addCar(car);
        carFleet.removeCar("AB-1234");
        assertFalse(carFleet.getAllCars().contains(car));
    }

    @Test
    public void testRemoveNonExistingCar() {
        assertThrows(CarNotFoundException.class, () -> {
            carFleet.removeCar("AB-1234");
        });
    }

    @Test
    public void testUpdateCar() {
        Car car = new Car("AB-1234", "Toyota", "Corolla", 100);
        car.setAvailable(true);
        carFleet.addCar(car);
        Car updatedCar = new Car("AB-1234", "Toyota", "Camry", 200);
        car.setAvailable(true);
        Car result = carFleet.updateCar("AB-1234", updatedCar);
        assertEquals(updatedCar.getBrand(), result.getBrand());
        assertEquals(updatedCar.getType(), result.getType());
        assertEquals(updatedCar.getPrice(), result.getPrice());
    }





    @Test
    public void testSearchCar() {
        Car car = new Car("AB-1234", "Toyota", "Corolla", 100);
        car.setAvailable(true);
        carFleet.addCar(car);
        Car result = carFleet.searchCar("AB-1234");
        assertEquals(car, result);
    }

    @Test
    public void testSearchNonExistingCar() {
        assertThrows(NullPointerException.class, () -> {
            carFleet.searchCar("AB-1234");
        });
    }

    @Test
    public void testReserveCar() {
        Car car = new Car("AB-1234", "Toyota", "Corolla", 100);
        car.setAvailable(true);
        carFleet.addCar(car);
        Car result = carFleet.reserveCar("AB-1234");
        assertFalse(result.isAvailable());
    }

    @Test
    public void testRentCar() {
        Car car = new Car("AB-1234", "Toyota", "Corolla", 100);
        car.setAvailable(true);

        carFleet.addCar(car);
        Car result = carFleet.rentCar("AB-1234");
        assertFalse(result.isAvailable());
    }

    @Test
    public void testReturnCar() {
        Car car = new Car("AB-1234", "Toyota", "Corolla", 100);
        car.setAvailable(true);
        carFleet.addCar(car);
        Car result = carFleet.returnCar("AB-1234");
        assertTrue(result.isAvailable());
    }
}
