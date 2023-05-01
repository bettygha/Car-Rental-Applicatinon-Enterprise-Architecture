package car_rental_app.service;

import car_rental_app.domain.RentedCar;

public class RentedCarAdapter {

    public static RentedCarDTO getRentedCarDTOFromRentedCar(RentedCar rentedCar){
        RentedCarDTO rentedCarDTO = new RentedCarDTO(rentedCar.getLicensePlate(),rentedCar.getRentedDate());
        return  rentedCarDTO;
    }
    public static RentedCar getRentedCarFromRentedCarDTO(RentedCarDTO rentedCarDTO){
        RentedCar rentedCar = new RentedCar(rentedCarDTO.getLicensePlate(),rentedCarDTO.getRentedDate());
        return  rentedCar;
    }
}
