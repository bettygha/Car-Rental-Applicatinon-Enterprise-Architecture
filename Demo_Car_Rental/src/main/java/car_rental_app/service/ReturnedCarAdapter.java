package car_rental_app.service;

import car_rental_app.domain.ReturnedCar;

public class ReturnedCarAdapter {

    public static ReturnedCarDTO getReturnedCarDTOFromReturnedCar(ReturnedCar returnedCar){
        ReturnedCarDTO returnedCarDTO = new ReturnedCarDTO(returnedCar.getLicensePlate(),returnedCar.getReturnedDate(),returnedCar.getPaymentAmount());
        return  returnedCarDTO;
    }
    public static ReturnedCar getReturnedCarFromReturnedCarDTO(ReturnedCarDTO returnedCarDTO){
        ReturnedCar returnedCar = new ReturnedCar(returnedCarDTO.getLicensePlate(),returnedCarDTO.getReturnedDate(),returnedCarDTO.getPaymentAmount());
        return  returnedCar;
    }
}
