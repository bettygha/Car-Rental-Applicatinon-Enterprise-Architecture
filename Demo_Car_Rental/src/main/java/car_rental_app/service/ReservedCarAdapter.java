package car_rental_app.service;

import car_rental_app.domain.ReservedCar;

public class ReservedCarAdapter {
    public static ReservedCarDTO getReservedCarDtoFromReservedCar(ReservedCar reservedCar){
        ReservedCarDTO reservedCarDTO = new ReservedCarDTO(reservedCar.getLicensePlate(),reservedCar.getReservedDate());
        return  reservedCarDTO;
    }
    public static ReservedCar getReservedCarDtoFromReservedCar(ReservedCarDTO reservedCarDTO){
        ReservedCar reservedCar = new ReservedCar(reservedCarDTO.getLicensePlate(),reservedCarDTO.getReservedDate());
        return  reservedCar;
    }

}
