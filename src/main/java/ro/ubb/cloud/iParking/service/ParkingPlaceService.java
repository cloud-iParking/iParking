package ro.ubb.cloud.iParking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.cloud.iParking.model.dto.ParkingPlaceDTO;
import ro.ubb.cloud.iParking.model.entities.ParkingPlace;
import ro.ubb.cloud.iParking.model.entities.Street;
import ro.ubb.cloud.iParking.model.entities.User;
import ro.ubb.cloud.iParking.model.transformers.impl.ParkingPlaceTransformer;
import ro.ubb.cloud.iParking.model.transformers.impl.StreetTransformer;
import ro.ubb.cloud.iParking.model.transformers.impl.UserTransformer;
import ro.ubb.cloud.iParking.repo.ParkingPlaceRepository;
import ro.ubb.cloud.iParking.repo.StreetRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParkingPlaceService {

    @Autowired
    private ParkingPlaceRepository parkingPlaceRepository;
    @Autowired
    private StreetRepository streetRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private StreetService streetService;
    @Autowired
    private ParkingPlaceTransformer parkingPlaceTransformer;

    public List<ParkingPlaceDTO> getAllParkingPlaces() {
        List<ParkingPlace> parkingPlaceList = parkingPlaceRepository.findAll();
        return parkingPlaceList.stream().map(parkingPlaceTransformer::toDTO).collect(Collectors.toList());
    }

    public List<ParkingPlaceDTO> getAllAvailableParkingPlaces(String streetName, String currentTime) {
        Street street = streetRepository.findAllByName(streetName).get(0);
        Timestamp currentTimeAsTimestamp = Timestamp.valueOf(currentTime);

        List<ParkingPlace> availableParkingPlaces = parkingPlaceRepository
                .findAllByStreetAndAvailableFromLessThanAndAvailableUntilGreaterThan(street, currentTimeAsTimestamp, currentTimeAsTimestamp);

        return availableParkingPlaces.stream().map(parkingPlaceTransformer::toDTO).collect(Collectors.toList());
    }

    public ParkingPlaceDTO addNewParkingPlace(ParkingPlaceDTO parkingPlaceDTO) {
        User user = userService.retrieveOrCreateUser(parkingPlaceDTO.getUser());
        Street street = streetService.retrieveOrCreateStreet(parkingPlaceDTO.getStreet());

        ParkingPlace parkingPlaceToAdd = parkingPlaceTransformer.toEntity(parkingPlaceDTO);
        parkingPlaceToAdd.setUser(user);
        parkingPlaceToAdd.setStreet(street);
        ParkingPlace addedParkingPlace = parkingPlaceRepository.save(parkingPlaceToAdd);

        return parkingPlaceTransformer.toDTO(addedParkingPlace);
    }

    public ParkingPlaceDTO updatedParkingPlace(ParkingPlaceDTO parkingPlaceDTO, Optional<ParkingPlace> parkingPlaceFromDb) {
        User user = userService.retrieveOrCreateUser(parkingPlaceDTO.getUser());
        Street street = streetService.retrieveOrCreateStreet(parkingPlaceDTO.getStreet());

        ParkingPlace parkingPlaceFromDB = parkingPlaceFromDb.orElseThrow(() -> new RuntimeException("No such parking place."));

        parkingPlaceFromDB.setNumber(parkingPlaceDTO.getNumber());
        parkingPlaceFromDB.setAvailableFrom(parkingPlaceDTO.getAvailableFrom());
        parkingPlaceFromDB.setAvailableUntil(parkingPlaceDTO.getAvailableUntil());
        parkingPlaceFromDB.setIsFree(parkingPlaceDTO.getIsFree());
        parkingPlaceFromDB.setStreet(street);
        parkingPlaceFromDB.setUser(user);

        ParkingPlace updatedParkingPlace = parkingPlaceRepository.save(parkingPlaceFromDB);
        return parkingPlaceTransformer.toDTO(updatedParkingPlace);
    }

    public void deleteParkingPlace(Integer id) {
        ParkingPlace parkingPlaceToDelete = parkingPlaceRepository.findById(id).orElseThrow(() -> new RuntimeException("No such parking place."));
        parkingPlaceRepository.delete(parkingPlaceToDelete);
    }
}
