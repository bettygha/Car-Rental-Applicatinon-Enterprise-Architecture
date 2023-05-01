package car_rental_app.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceLogger {
    Logger logger = LoggerFactory.getLogger(CustomerServiceLogger.class);

    public void addCustomer(){
        logger.info("Customer is added ....");
    }
    public void updateCustomer(){
        logger.info("Customer is updated ....");
    }
    public void searchCustomer(){
        logger.info("Customer is searched....");
    }
    public void removeCustomer(){
        logger.info("Customer is removed ....");
    }


}
