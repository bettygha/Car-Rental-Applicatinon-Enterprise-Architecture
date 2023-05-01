package car_fleet_app.service.messaging;

import car_fleet_app.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@EnableJms
public class ReservationListener {

    @Autowired
    private CarService carService;

    @JmsListener(destination = "reservation2")
    public void reserveMessage(final String licencePlate){
        System.out.println("##################################   Car Reservation request coming .......#################################################################");
        carService.reserveCar(licencePlate);
        System.out.println("Car with licencePlate "+ licencePlate +" has been reserved");
        System.out.println("##################################   Car Reservation done .......#################################################################");


    }

}
