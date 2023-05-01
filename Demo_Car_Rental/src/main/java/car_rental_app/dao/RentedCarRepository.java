package car_rental_app.dao;

import car_rental_app.domain.RentedCar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentedCarRepository extends JpaRepository<RentedCar,Long> {
}
