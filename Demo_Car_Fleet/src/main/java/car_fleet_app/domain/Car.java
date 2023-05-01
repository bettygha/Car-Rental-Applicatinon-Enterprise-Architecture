package car_fleet_app.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;

@Document
public class Car {

    @Id
    private String licencePlate;
    private String brand;
    private String type;
    private long price;

    private boolean isAvailable ;

   // private long quantity ;

    public Car(){}

    public Car(String licencePlate, String brand, String type, long price) {
        this.licencePlate = licencePlate;
        this.brand = brand;
        this.type = type;
        this.price = price;
        this.isAvailable = true;
        //this.quantity = quantity;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Car{" +
                "licencePlate='" + licencePlate + '\'' +
                ", brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
