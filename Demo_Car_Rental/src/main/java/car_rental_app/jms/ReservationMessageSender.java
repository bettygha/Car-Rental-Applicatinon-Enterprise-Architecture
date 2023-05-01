package car_rental_app.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@EnableJms
public class ReservationMessageSender {

    @Autowired
    JmsTemplate jmsTemplate;
    public void sendReservationMessage(String licensePlate){

        jmsTemplate.convertAndSend("reservation2",licensePlate);
        System.out.println("############################### Car Reservation Sent............");
    }

}
