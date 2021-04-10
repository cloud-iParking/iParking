package ro.ubb.cloud.iParking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.cloud.iParking.model.dto.ReportDTO;
import ro.ubb.cloud.iParking.model.dto.ReservationDTO;
import ro.ubb.cloud.iParking.model.dto.UserDTO;
import ro.ubb.cloud.iParking.service.UserService;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/user")
@Produces("application/json")
@Consumes("application/json;charset=UTF-8")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok().body(this.userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<String> saveUser(@RequestBody UserDTO userDTO) {
        UserDTO response = userService.save(userDTO);
        if (response != null) {
            return ResponseEntity.ok().body("User saved!");
        }
        return ResponseEntity.badRequest().body("User save failed!");
    }

    @PostMapping("/reportLoaner")
    public ResponseEntity<UserDTO> reportLoaner(@RequestBody ReportDTO reportDTO) {
        try {
            userService.reportLoaner(reportDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/reportBorrower")
    public ResponseEntity<UserDTO> reportBorrower(@RequestBody ReportDTO report) {
        try {
            userService.reportBorrower(report);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/reservations/made")
    public ResponseEntity<List<ReservationDTO>> getReservationsMade(@PathVariable(name = "id") int id) {
        try {
            return ResponseEntity.ok(userService.getReservationsMade(id));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/reservations/received")
    public ResponseEntity<List<ReservationDTO>> getReservationsReceived(@PathVariable(name = "id") int id) {
        try {
            return ResponseEntity.ok(userService.getReservationsReceived(id));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
