package ro.ubb.cloud.iParking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.cloud.iParking.model.dto.ReservationDTO;
import ro.ubb.cloud.iParking.service.ReservationService;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/reservation")
@Produces("application/json")
@Consumes("application/json;charset=UTF-8")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/")
    public ResponseEntity<ReservationDTO> addNewReservation(@RequestBody ReservationDTO reservationDTO) {
        try {
            return new ResponseEntity<>(reservationService.addNewReservation(reservationDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
