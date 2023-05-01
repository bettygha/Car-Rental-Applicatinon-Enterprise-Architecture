package car_rental_app.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class RentedCar {
    @Id
    @GeneratedValue
    private long id;
    private String licensePlate;
    private Date rentedDate;

    public RentedCar() {
    }

    public RentedCar(String licensePlate, Date rentedDate) {
        this.licensePlate = licensePlate;
        this.rentedDate = rentedDate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Date getRentedDate() {
        return rentedDate;
    }

    public void setRentedDate(Date rentedDate) {
        this.rentedDate = rentedDate;
    }

    @Override
    public String toString() {
        return "RentedCar{" +
                "licensePlate='" + licensePlate + '\'' +
                ", rentedDate=" + rentedDate +
                '}';
    }
}
