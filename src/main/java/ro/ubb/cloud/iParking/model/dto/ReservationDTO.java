package ro.ubb.cloud.iParking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationDTO {

    private Integer id;

    private Boolean isActive;

    private Timestamp timestamp;

    private ParkingPlaceDTO parkingPlace;

    private UserDTO loaner;
}
