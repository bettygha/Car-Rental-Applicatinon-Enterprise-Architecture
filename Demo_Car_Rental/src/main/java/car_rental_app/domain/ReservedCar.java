package car_rental_app.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class ReservedCar {
    @Id
    @GeneratedValue
    private long id;
    private String licensePlate;
    private Date reservedDate;


    public ReservedCar() {
    }

    public ReservedCar(String licensePlate, Date reservedDate) {
        this.licensePlate = licensePlate;
        this.reservedDate = reservedDate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Date getReservedDate() {
        return reservedDate;
    }

    public void setReservedDate(Date reservedDate) {
        this.reservedDate = reservedDate;
    }

    @Override
    public String toString() {
        return "ReservedCar{" +
                "id=" + id +
                ", licensePlate='" + licensePlate + '\'' +
                ", reservedDate=" + reservedDate +
                '}';
    }
}
