package ro.ubb.cloud.iParking.utils.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ro.ubb.cloud.iParking.model.entities.ParkingPlace;
import ro.ubb.cloud.iParking.model.entities.User;
import ro.ubb.cloud.iParking.repo.LoginRepository;
import ro.ubb.cloud.iParking.repo.ParkingPlaceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@EnableScheduling
@Configuration
public class SchedulerConfig {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private ParkingPlaceRepository parkingPlaceRepository;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Scheduled(fixedDelay = 5000)
    public void sendNotifications(){
        List<ParkingPlace> pargkingPlaces = new ArrayList<>();
        List<User> userList = loginRepository.findAll();
        for(User userr: userList) {
            Optional<User> user = loginRepository.findByUsername(userr.getUsername());
            if (user.isPresent()) {
                pargkingPlaces = parkingPlaceRepository.findAllByUserAndIsFree(user.get(), true);
                for (ParkingPlace place : pargkingPlaces) {
                    if (place.getIsFree().equals(true)) {
                        simpMessagingTemplate.convertAndSend("/topic/place-notification", place);
                        System.out.println("User is :" + user.get().getUsername());
                        System.out.println("And his free places are:" + place);
                    }
                }
            }
        }
    }
}
