package car_fleet_app.service;

import car_fleet_app.dao.CarFleetRepository;
import car_fleet_app.domain.CarFleet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarFleetService {

    @Autowired
    private CarFleetRepository carFleetRepository;
    public void createCarFleet(Long id){
        CarFleet carFleet = new CarFleet(id);
        carFleetRepository.save(carFleet);

    }
}
