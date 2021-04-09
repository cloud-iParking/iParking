package ro.ubb.cloud.iParking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.cloud.iParking.model.dto.StreetDTO;
import ro.ubb.cloud.iParking.model.entities.Street;
import ro.ubb.cloud.iParking.model.transformers.impl.StreetTransformer;
import ro.ubb.cloud.iParking.repo.StreetRepository;

@Service
public class StreetService {

    @Autowired
    private StreetRepository streetRepository;
    @Autowired
    private StreetTransformer streetTransformer;
    @Autowired
    private DistrictService districtService;

    public Street retrieveOrCreateStreet(StreetDTO streetDTO) {
        if (streetDTO != null && streetDTO.getName() != null && !streetDTO.getName().equals("")) {
            return streetRepository.findAllByName(streetDTO.getName()).get(0);
        } else {
            Street street = streetTransformer.toEntity(streetDTO);
            street.setDistrict(districtService.retrieveOrCreateDistrict(streetDTO.getDistrict()));

            return streetRepository.save(streetTransformer.toEntity(streetDTO));
        }
    }
}
