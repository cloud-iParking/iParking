package ro.ubb.cloud.iParking.model.transformers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.cloud.iParking.model.dto.StreetDTO;
import ro.ubb.cloud.iParking.model.entities.Street;
import ro.ubb.cloud.iParking.model.transformers.Transformer;

@Component
public class StreetTransformer implements Transformer<Street, StreetDTO> {
    @Autowired
    private DistrictTransformer districtTransformer;

    @Override
    public StreetDTO toDTO(Street entity) {
        return StreetDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .district(districtTransformer.toDTO(entity.getDistrict()))
                .build();
    }

    @Override
    public Street toEntity(StreetDTO dto) {
        return Street.builder()
                .id(dto.getId())
                .name(dto.getName())
                .district(districtTransformer.toEntity(dto.getDistrict()))
                .build();
    }
}
