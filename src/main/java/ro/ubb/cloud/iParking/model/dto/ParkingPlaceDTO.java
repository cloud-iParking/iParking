package ro.ubb.cloud.iParking.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ParkingPlaceDTO {

    private Integer id;
    @JsonProperty("parkingPlaceNumber")
    private Integer number;
    private Timestamp availableFrom;
    private Timestamp availableUntil;
    private Boolean isFree;
    private StreetDTO street;
    private UserDTO user;
}
