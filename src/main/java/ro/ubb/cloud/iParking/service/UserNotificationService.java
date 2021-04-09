package ro.ubb.cloud.iParking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ro.ubb.cloud.iParking.model.entities.ParkingPlace;
import ro.ubb.cloud.iParking.model.entities.User;
import ro.ubb.cloud.iParking.repo.LoginRepository;
import ro.ubb.cloud.iParking.repo.ParkingPlaceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserNotificationService {

    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private ParkingPlaceRepository parkingPlaceRepository;

    @Autowired
    public UserNotificationService(SimpMessagingTemplate messagingTemplate){
        this.simpMessagingTemplate = messagingTemplate;
    }

    public List<ParkingPlace> notifyUser(String username){
        List<ParkingPlace> pargkingPlaces = new ArrayList<>();
        Optional<User> user = loginRepository.findByUsername(username);
        if(user.isPresent()) {
            pargkingPlaces = parkingPlaceRepository.findAllByUserAndIsFree(user.get(), true);
            for (ParkingPlace place : pargkingPlaces) {
                simpMessagingTemplate.convertAndSend("/topic/place-notification", place);
            }
        }else{
            System.out.println("Empty");
        }
    return pargkingPlaces;
    }
}
