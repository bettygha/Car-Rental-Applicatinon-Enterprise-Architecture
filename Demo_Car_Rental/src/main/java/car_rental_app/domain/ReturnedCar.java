package car_rental_app.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class ReturnedCar {
    @Id
    @GeneratedValue
    private long id;
    private String licensePlate;
    private Date returnedDate;
    private long paymentAmount;

    public ReturnedCar() {
    }

    public ReturnedCar(String licensePlate, Date returnedDate, long paymentAmount) {
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
        return "ReturnedCar{" +
                "id=" + id +
                ", licensePlate='" + licensePlate + '\'' +
                ", returnedDate=" + returnedDate +
                ", paymentAmount=" + paymentAmount +
                '}';
    }
}
