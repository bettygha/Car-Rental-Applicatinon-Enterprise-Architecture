package employee;


import java.util.ArrayList;
import java.util.List;

public class CustomerDTO {
    private String customerNumber;
    private String name;
    private String email;
    private List<RentedCarDTO> rentedCars;
    private List<ReservedCarDTO> reservedCars;
    private List<ReturnedCarDTO> returnedCars;

    private int point ;
    private String level;


    public CustomerDTO(){};

    public CustomerDTO(String customerNumber, String name, String email) {
        this.customerNumber = customerNumber;
        this.name = name;
        this.email = email;
        this.rentedCars= new ArrayList<>();
        this.reservedCars = new ArrayList<>();
        this.returnedCars = new ArrayList<>();
        this.point = 0;
        this.level = "Starter";


    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RentedCarDTO> getRentedCars() {
        return rentedCars;
    }

    public void addRentedCar(RentedCarDTO rentedCar) {
        this.rentedCars.add(rentedCar);
    }

    public List<ReservedCarDTO> getReservedCars() {
        return reservedCars;
    }

    public void addReservedCar(ReservedCarDTO reservedCar) {
        this.reservedCars.add(reservedCar);
    }

    public List<ReturnedCarDTO> getReturnedCars() {
        return returnedCars;
    }

    public void addReturnedCars(ReturnedCarDTO returnedCar) {
        this.returnedCars.add(returnedCar);
    }

    public void setRentedCars(List<RentedCarDTO> rentedCars) {
        this.rentedCars = rentedCars;
    }

    public void setReservedCars(List<ReservedCarDTO> reservedCars) {
        this.reservedCars = reservedCars;
    }

    public void setReturnedCars(List<ReturnedCarDTO> returnedCars) {
        this.returnedCars = returnedCars;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customerNumber='" + customerNumber + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", rentedCars=" + rentedCars +
                ", reservedCars=" + reservedCars +
                ", returnedCars=" + returnedCars +
                ", point=" + point +
                ", level='" + level + '\'' +
                '}';
    }
}
