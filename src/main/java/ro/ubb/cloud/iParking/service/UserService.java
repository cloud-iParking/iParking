package ro.ubb.cloud.iParking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.cloud.iParking.model.dto.ReportDTO;
import ro.ubb.cloud.iParking.model.dto.ReservationDTO;
import ro.ubb.cloud.iParking.model.dto.UserDTO;
import ro.ubb.cloud.iParking.model.entities.Report;
import ro.ubb.cloud.iParking.model.entities.Reservation;
import ro.ubb.cloud.iParking.model.entities.User;
import ro.ubb.cloud.iParking.model.transformers.impl.ReportTransformer;
import ro.ubb.cloud.iParking.model.transformers.impl.ReservationTransformer;
import ro.ubb.cloud.iParking.model.transformers.impl.UserTransformer;
import ro.ubb.cloud.iParking.repo.ReportRepository;
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
    private ReportRepository reportRepository;

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

        Optional<User> optionalReportedUser = userRepository.findById(reportDTO.getReservation().getLoaner().getId());
        if (optionalReportedUser.isPresent()) {
            reportRepository.save(reportTransformer.toEntity(reportDTO));
            User reportedUser = optionalReportedUser.get();
            reportedUser.setReportNumber(reportedUser.getReportNumber() + 1);
            userRepository.save(reportedUser);
        } else {
            throw new RuntimeException("User not found. Report not registered");
        }
    }

    public void reportBorrower(ReportDTO reportDTO) {
        Optional<User> optionalReportedUser = userRepository.findById(reportDTO.getReservation().getParkingPlace()
                .getUser().getId());
        if (optionalReportedUser.isPresent()) {
            Report report = new Report();
            report.setReservation(reservationTransformer.toEntity(reportDTO.getReservation()));
            report.setDescription(reportDTO.getDescription());
            reportRepository.save(report);
            User reportedUser = optionalReportedUser.get();
            reportedUser.setReportNumber(reportedUser.getReportNumber() + 1);
            userRepository.save(reportedUser);
        } else {
            throw new RuntimeException("User not found. Report not registered");
        }
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
            List<Reservation> reservationList = reservationRepository.findAll();
            for (Reservation reservation : reservationList) {

                if(reservation.getParkingPlace().getUser().getId().equals(userId)) {
                    reservations.add(reservationTransformer.toDTO(reservation));
                }
            }
            return reservations;
        }
        return new ArrayList<>();
    }

    public void blockUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User userToBeSaved = optionalUser.get();
            userToBeSaved.setIsBlocked(!userToBeSaved.getIsBlocked());

            userRepository.save(userToBeSaved);
        } else {
            throw new RuntimeException("User not found.");
        }
    }
}
