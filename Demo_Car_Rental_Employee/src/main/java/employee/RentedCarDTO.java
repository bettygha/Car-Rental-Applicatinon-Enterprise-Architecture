package employee;

import java.util.Date;

public class RentedCarDTO {

    private String licensePlate;
    private Date rentedDate;

    public RentedCarDTO() {
    }

    public RentedCarDTO(String licensePlate, Date rentedDate) {
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
