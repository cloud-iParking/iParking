package ro.ubb.cloud.iParking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.cloud.iParking.model.dto.ParkingPlaceDTO;
import ro.ubb.cloud.iParking.model.entities.ParkingPlace;
import ro.ubb.cloud.iParking.repo.ParkingPlaceRepository;
import ro.ubb.cloud.iParking.service.ParkingPlaceService;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/parkingplaces")
@Produces("application/json")
@Consumes("application/json;charset=UTF-8")
public class ParkingPlaceController {

    private final ParkingPlaceService parkingPlaceService;
    private final ParkingPlaceRepository parkingPlaceRepository;

    @Autowired
    public ParkingPlaceController(ParkingPlaceService parkingPlaceService, ParkingPlaceRepository parkingPlaceRepository) {
        this.parkingPlaceService = parkingPlaceService;
        this.parkingPlaceRepository = parkingPlaceRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<ParkingPlaceDTO>> getAllParkingPlaces() {
        return ResponseEntity.ok().body(parkingPlaceService.getAllParkingPlaces());
    }

    @GetMapping("/availableParkingPlaces/")
    public ResponseEntity<List<ParkingPlaceDTO>> getAllAvailableParkingPlaces(@RequestParam String street, @RequestParam String currentTime) {
        return new ResponseEntity<>(parkingPlaceService.getAllAvailableParkingPlaces(street, currentTime), HttpStatus.OK);
    }

    @GetMapping("/ownedBy/")
    public ResponseEntity<List<ParkingPlaceDTO>> getAllParkingPlacesOwnedByGivenUser(@RequestParam String username) {
        try {
            return new ResponseEntity<>(parkingPlaceService.getAllParkingPlacesOfGivenUser(username), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<ParkingPlaceDTO> addNewParkingPlace(@RequestBody ParkingPlaceDTO parkingPlaceDTO) {
        try {
            return new ResponseEntity<>(parkingPlaceService.addNewParkingPlace(parkingPlaceDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ParkingPlaceDTO> updateParkingPlace(@RequestBody ParkingPlaceDTO parkingPlaceDTO) {
        try {
            Optional<ParkingPlace> parkingPlaceFromDB = parkingPlaceRepository.findById(parkingPlaceDTO.getId());
            if(parkingPlaceFromDB.isPresent()) {
                return new ResponseEntity<>(parkingPlaceService.updatedParkingPlace(parkingPlaceDTO, parkingPlaceFromDB), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(parkingPlaceService.addNewParkingPlace(parkingPlaceDTO), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteParkingPlace(@RequestParam Integer id) {
        try {
            parkingPlaceService.deleteParkingPlace(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
