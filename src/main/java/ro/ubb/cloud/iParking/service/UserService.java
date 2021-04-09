package ro.ubb.cloud.iParking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.cloud.iParking.model.dto.ReportDTO;
import ro.ubb.cloud.iParking.model.dto.ReservationDTO;
import ro.ubb.cloud.iParking.model.dto.UserDTO;
import ro.ubb.cloud.iParking.model.entities.Reservation;
import ro.ubb.cloud.iParking.model.entities.User;
import ro.ubb.cloud.iParking.model.transformers.impl.ReportTransformer;
import ro.ubb.cloud.iParking.model.transformers.impl.ReservationTransformer;
import ro.ubb.cloud.iParking.model.transformers.impl.UserTransformer;
import ro.ubb.cloud.iParking.repo.ReservationRepository;
import ro.ubb.cloud.iParking.repo.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTransformer userTransformer;

    @Autowired
    private ReportTransformer reportTransformer;

    @Autowired
    private ReservationTransformer reservationTransformer;

    @Autowired
    private ReservationRepository reservationRepository;

    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(userTransformer::toDTO).collect(Collectors.toList());
    }

    public Optional<User> getUserById(Integer userId) {
        return this.userRepository.findById(userId);
    }

    public Optional<User> getUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public UserDTO save(UserDTO userDTO) {
        User user = userRepository.save(userTransformer.toEntity(userDTO));
        return userTransformer.toDTO(user);
    }

    public User retrieveOrCreateUser(UserDTO userDTO) {
        if (userDTO != null && userDTO.getUsername() != null && !userDTO.getUsername().equals("")) {
            return userRepository.findByUsername(userDTO.getUsername()).orElseThrow(() -> new RuntimeException("No such user."));
        } else {
            return userRepository.save(userTransformer.toEntity(userDTO));
        }
    }

    public void reportLoaner(ReportDTO reportDTO) {
//        Report report = reportTransformer.toEntity(reportDTO);
//        User loaner = report.getReservation().getLoaner();
//        loaner.getReports().add(report);
//        userRepository.save(loaner);
    }

    public void reportBorrower(ReportDTO reportDTO) {
//        Report report = reportTransformer.toEntity(reportDTO);
//        User borrower = report.getReservation().getParkingPlace().getUser();
//        borrower.getReports().add(report);
//        userRepository.save(borrower);
    }

    public List<ReservationDTO> getReservationsMade(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            ArrayList<ReservationDTO> reservations = new ArrayList<>();
            for (Reservation reservation : reservationRepository.getAllReservationsMadeByUserId(userId)) {
                reservations.add(reservationTransformer.toDTO(reservation));
            }
            return reservations;
        }
        return new ArrayList<>();
    }

    public List<ReservationDTO> getReservationsReceived(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            ArrayList<ReservationDTO> reservations = new ArrayList<>();
            for (Reservation reservation : reservationRepository.getAllReservationsReceivedByUserId(userId)) {
                reservations.add(reservationTransformer.toDTO(reservation));
            }
            return reservations;
        }
        return new ArrayList<>();
    }
}
