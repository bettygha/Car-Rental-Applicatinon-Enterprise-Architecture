package client.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@EnableJms
public class MessageListener {

    @JmsListener(destination = "quantity")
    public void reserveMessage(final String message){
        System.out.println("################ Car  Quantity Notification received  ...... .......#################################################################");
        System.out.println(">>>>>   "+ message);
    }

}
