package car_rental_app.service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
public class ReturnedCarDTO {

    private String licensePlate;
    private Date returnedDate;
    private long paymentAmount;

    public ReturnedCarDTO() {
    }

    public ReturnedCarDTO(String licensePlate, Date returnedDate, long paymentAmount) {
        this.licensePlate = licensePlate;
        this.returnedDate = returnedDate;
        this.paymentAmount = paymentAmount;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    public long getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(long paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Override
    public String toString() {
        return "ReturnedCarDTO{" +
                "licensePlate='" + licensePlate + '\'' +
                ", returnedDate=" + returnedDate +
                ", paymentAmount=" + paymentAmount +
                '}';
    }
}
