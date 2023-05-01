package car_rental_app.dao;

import car_rental_app.domain.ReturnedCar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReturnedCarRepository extends JpaRepository<ReturnedCar,Long> {
}
