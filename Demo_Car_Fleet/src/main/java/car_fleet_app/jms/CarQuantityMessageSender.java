package car_fleet_app.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@EnableJms
public class CarQuantityMessageSender {

    @Autowired
    JmsTemplate jmsTemplate;
    public void sendCarQuantityMessage(String message){

        jmsTemplate.convertAndSend("quantity",message);
        System.out.println("################################## Car  Quantity Notification Sent...... .......#################################################################");


    }

}
