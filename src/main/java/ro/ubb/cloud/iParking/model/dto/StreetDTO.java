package ro.ubb.cloud.iParking.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StreetDTO {
    private Integer id;
    private String name;
    private DistrictDTO district;
}
