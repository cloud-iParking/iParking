package ro.ubb.cloud.iParking.repo;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ubb.cloud.iParking.model.entities.ParkingPlace;
import ro.ubb.cloud.iParking.model.entities.Street;
import ro.ubb.cloud.iParking.model.entities.User;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ParkingPlaceRepository extends JpaRepository<ParkingPlace, Integer> {
    List<ParkingPlace> findAllByStreetAndAvailableFromLessThanAndAvailableUntilGreaterThan(Street street, Timestamp currentTimeOne, Timestamp currentTimeTwo);
    List<ParkingPlace> findAllByUserAndIsFree(User user, Boolean isFree);
    List<ParkingPlace> findAllByUser(User user);
}
