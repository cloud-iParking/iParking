package ro.ubb.cloud.iParking.model.transformers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.cloud.iParking.model.dto.ReservationDTO;
import ro.ubb.cloud.iParking.model.entities.Reservation;
import ro.ubb.cloud.iParking.model.transformers.Transformer;

@Component
public class ReservationTransformer implements Transformer<Reservation, ReservationDTO> {

    @Autowired
    UserTransformer userTransformer;

    @Autowired
    ParkingPlaceTransformer parkingPlaceTransformer;

    @Override
    public ReservationDTO toDTO(Reservation entity) {
        ReservationDTO reservationDTO = new ReservationDTO();
        if (entity != null) {
            reservationDTO.setId(entity.getId());
            reservationDTO.setLoaner(userTransformer.toDTO(entity.getLoaner()));
            reservationDTO.setIsActive(entity.getIsActive());
            reservationDTO.setTimestamp(entity.getTimestamp());
            reservationDTO.setParkingPlace(parkingPlaceTransformer.toDTO(entity.getParkingPlace()));
        }
        return reservationDTO;
    }

    @Override
    public Reservation toEntity(ReservationDTO dto) {
        Reservation reservation = new Reservation();
        if (dto != null) {
            reservation.setId(dto.getId());
            reservation.setLoaner(userTransformer.toEntity(dto.getLoaner()));
            reservation.setIsActive(dto.getIsActive());
            reservation.setTimestamp(dto.getTimestamp());
            reservation.setParkingPlace(parkingPlaceTransformer.toEntity(dto.getParkingPlace()));
        }
        return reservation;
    }
}
