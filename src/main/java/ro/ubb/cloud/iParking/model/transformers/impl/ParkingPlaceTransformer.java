package ro.ubb.cloud.iParking.model.transformers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.cloud.iParking.model.dto.ParkingPlaceDTO;
import ro.ubb.cloud.iParking.model.entities.ParkingPlace;
import ro.ubb.cloud.iParking.model.transformers.Transformer;

@Component
public class ParkingPlaceTransformer implements Transformer<ParkingPlace, ParkingPlaceDTO> {

    @Autowired
    private UserTransformer userTransformer;
    @Autowired
    private StreetTransformer streetTransformer;

    @Override
    public ParkingPlaceDTO toDTO(ParkingPlace entity) {
        return ParkingPlaceDTO.builder()
                .id(entity.getId())
                .number(entity.getNumber())
                .availableFrom(entity.getAvailableFrom())
                .availableUntil(entity.getAvailableUntil())
                .isFree(entity.getIsFree())
                .street(streetTransformer.toDTO(entity.getStreet()))
                .user(userTransformer.toDTO(entity.getUser()))
                .build();
    }

    @Override
    public ParkingPlace toEntity(ParkingPlaceDTO dto) {
        return ParkingPlace.builder()
                .id(dto.getId())
                .number(dto.getNumber())
                .availableFrom(dto.getAvailableFrom())
                .availableUntil(dto.getAvailableUntil())
                .isFree(dto.getIsFree())
                .street(streetTransformer.toEntity(dto.getStreet()))
                .user(userTransformer.toEntity(dto.getUser()))
                .build();
    }
}
