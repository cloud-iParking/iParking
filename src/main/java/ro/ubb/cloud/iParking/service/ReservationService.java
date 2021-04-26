package ro.ubb.cloud.iParking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.cloud.iParking.model.dto.ReservationDTO;
import ro.ubb.cloud.iParking.model.entities.ParkingPlace;
import ro.ubb.cloud.iParking.model.entities.Reservation;
import ro.ubb.cloud.iParking.model.entities.User;
import ro.ubb.cloud.iParking.model.transformers.impl.ParkingPlaceTransformer;
import ro.ubb.cloud.iParking.model.transformers.impl.ReservationTransformer;
import ro.ubb.cloud.iParking.repo.ReservationRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ParkingPlaceService parkingPlaceService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReservationTransformer reservationTransformer;
    @Autowired
    private ParkingPlaceTransformer parkingPlaceTransformer;

    public ReservationDTO addNewReservation(ReservationDTO reservationDTO) {
        ParkingPlace reservedParkingPlace = parkingPlaceTransformer.toEntity(
                parkingPlaceService.changeParkingPlaceStatus(reservationDTO.getParkingPlace().getId()));

        User loaner = userService.retrieveOrCreateUser(reservationDTO.getLoaner());

        Reservation reservation = Reservation.builder()
                .id(0)
                .loaner(loaner)
                .parkingPlace(reservedParkingPlace)
                .timestamp(Timestamp.from(Instant.now()))
                .build();

        Reservation savedReservation;

        if (!reservedParkingPlace.getIsFree()) {
            reservation.setIsActive(true);
            savedReservation = this.reservationRepository.save(reservation);
        } else {
            reservation.setIsActive(false);
            savedReservation = this.updateReservation(reservation);
        }

        return reservationTransformer.toDTO(savedReservation);
    }

    public Reservation updateReservation(Reservation reservation) {
        List<Reservation> reservationList = this.reservationRepository.findAllByLoanerIdAndParkingPlaceIdOrderByTimestampDesc(
                reservation.getLoaner().getId(), reservation.getParkingPlace().getId());

        Reservation reservationToUpdate = reservationList.size() > 0 ? reservationList.get(0) : reservation;
        reservationToUpdate.setIsActive(reservation.getIsActive());

        return this.reservationRepository.save(reservationToUpdate);
    }
}
