package ro.ubb.cloud.iParking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.ubb.cloud.iParking.model.entities.Reservation;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query(value = "SELECT * FROM iparking.reservation WHERE loaner_id = ?1", nativeQuery = true)
    List<Reservation> getAllReservationsMadeByUserId(Integer id);

    @Query(value = "SELECT * FROM iparking.reservation WHERE parking_place_id = (SELECT id FROM parking_place WHERE user_id = 1)", nativeQuery = true)
    List<Reservation> getAllReservationsReceivedByUserId(Integer id);

    List<Reservation> findAllByLoanerIdAndParkingPlaceIdOrderByTimestampDesc(Integer loanerId, Integer parkingPlaceId);

}
