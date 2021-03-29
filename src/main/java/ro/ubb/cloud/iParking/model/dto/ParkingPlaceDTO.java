package ro.ubb.cloud.iParking.model.dto;

import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ParkingPlaceDTO {

    private Integer id;
    private Integer number;
    private Timestamp availableFrom;
    private Timestamp availableUntil;
    private Boolean isFree;
    private StreetDTO street;
    private UserDTO user;
}
