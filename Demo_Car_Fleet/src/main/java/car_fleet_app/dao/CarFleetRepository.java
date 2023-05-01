package car_fleet_app.dao;

import car_fleet_app.domain.Car;
import car_fleet_app.domain.CarFleet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarFleetRepository extends MongoRepository<CarFleet, Long> {


}

