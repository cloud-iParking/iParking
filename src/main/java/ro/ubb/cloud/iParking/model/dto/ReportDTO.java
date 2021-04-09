package ro.ubb.cloud.iParking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReportDTO {

    private Integer id;

    private String description;

    private ReservationDTO reservation;
}
