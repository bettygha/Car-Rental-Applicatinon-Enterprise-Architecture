package employee;

import java.util.Date;

public class ReservedCarDTO {

    private String licensePlate;
    private Date reservedDate;


    public ReservedCarDTO() {
    }

    public ReservedCarDTO(String licensePlate, Date reservedDate) {
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
        return "ReservedCarDTO{" +
                "licensePlate='" + licensePlate + '\'' +
                ", reservedDate=" + reservedDate +
                '}';
    }
}
