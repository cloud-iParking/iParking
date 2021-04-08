package ro.ubb.cloud.iParking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.cloud.iParking.model.dto.ParkingPlaceDTO;
import ro.ubb.cloud.iParking.model.dto.UserNotificationDTO;
import ro.ubb.cloud.iParking.model.entities.ParkingPlace;
import ro.ubb.cloud.iParking.service.UserNotificationService;

import java.util.List;

@RestController
public class UserNotificationController {


    @Autowired
    private UserNotificationService service;

    @PostMapping("/place-notification")
    public ResponseEntity<List<ParkingPlace>> placeNotification(@RequestBody UserNotificationDTO user){
        return ResponseEntity.ok(service.notifyUser(user.getUsername()));
    }
}
