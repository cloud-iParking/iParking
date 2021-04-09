package ro.ubb.cloud.iParking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.cloud.iParking.model.dto.DistrictDTO;
import ro.ubb.cloud.iParking.model.entities.District;
import ro.ubb.cloud.iParking.model.transformers.impl.DistrictTransformer;
import ro.ubb.cloud.iParking.repo.DistrictRepository;

@Service
public class DistrictService {

    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private DistrictTransformer districtTransformer;

    public District retrieveOrCreateDistrict(DistrictDTO districtDTO) {
        if (districtDTO != null && districtDTO.getName() != null && !districtDTO.getName().equals("")) {
            return districtRepository.findByName(districtDTO.getName()).orElseThrow(() -> new RuntimeException("No such district."));
        } else {
            return districtRepository.save(districtTransformer.toEntity(districtDTO));
        }
    }
}
