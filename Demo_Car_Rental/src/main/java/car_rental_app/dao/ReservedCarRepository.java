package car_rental_app.dao;

import car_rental_app.domain.ReservedCar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservedCarRepository extends JpaRepository<ReservedCar,Long> {
}
