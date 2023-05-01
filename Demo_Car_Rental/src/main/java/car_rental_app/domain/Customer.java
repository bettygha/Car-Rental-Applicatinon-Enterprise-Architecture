package car_rental_app.domain;

import org.springframework.jms.annotation.EnableJms;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {

    @Id
    private String customerNumber;
    private String name;
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "RentedCustomer")
    private List<RentedCar> rentedCars;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ReservedCustomer")
    private List<ReservedCar> reservedCars;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ReturnedCustomer")
    private List<ReturnedCar> returnedCars;
    private int point ;
    private String level;

    public Customer(){};

    public Customer(String customerNumber, String name, String email) {
        this.customerNumber = customerNumber;
        this.name = name;
        this.email = email;
        rentedCars = new ArrayList<>();
        reservedCars=new ArrayList<>();
        returnedCars= new ArrayList<>();
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

    public List<RentedCar> getRentedCars() {
        return rentedCars;
    }

    public void addRentedCar(RentedCar rentedCar) {
        this.rentedCars.add(rentedCar);
    }

    public List<ReservedCar> getReservedCars() {
        return reservedCars;
    }

    public void addReservedCar(ReservedCar reservedCar) {
        this.reservedCars.add(reservedCar);
    }

    public List<ReturnedCar> getReturnedCars() {
        return returnedCars;
    }

    public void addReturnedCars(ReturnedCar returnedCar) {
        this.returnedCars.add(returnedCar);
    }

    public void reserveCar(ReservedCar reservedCar){
        reservedCars.add(reservedCar);
    }
    public void returnCar(ReturnedCar returnedCar){
        returnedCars.add(returnedCar);
    }
    public void rentCar(RentedCar rentedCar){
        rentedCars.add(rentedCar);
    }

    public void setRentedCars(List<RentedCar> rentedCars) {
        this.rentedCars = rentedCars;
    }

    public void setReservedCars(List<ReservedCar> reservedCars) {
        this.reservedCars = reservedCars;
    }

    public void setReturnedCars(List<ReturnedCar> returnedCars) {
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
        return "Customer{" +
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
