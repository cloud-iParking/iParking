package ro.ubb.cloud.iParking.model.transformers.impl;

import org.springframework.stereotype.Component;
import ro.ubb.cloud.iParking.model.dto.DistrictDTO;
import ro.ubb.cloud.iParking.model.entities.District;
import ro.ubb.cloud.iParking.model.transformers.Transformer;

@Component
public class DistrictTransformer implements Transformer<District, DistrictDTO> {
    @Override
    public DistrictDTO toDTO(District entity) {
        return DistrictDTO.builder().id(entity.getId()).name(entity.getName()).build();
    }

    @Override
    public District toEntity(DistrictDTO dto) {
        return District.builder().id(dto.getId()).name(dto.getName()).build();
    }
}
