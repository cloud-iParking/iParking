package ro.ubb.cloud.iParking.service.dto;

import org.springframework.stereotype.Component;
import ro.ubb.cloud.iParking.model.User;

@Component
public class DtoMapping {

    private DtoMapping() {

    }

    public User dtoToUser(UserDTO userDTO) {
        User user = new User();

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setCarNumber(userDTO.getCarNumber());
        user.setIsAdmin(userDTO.getIsAdmin());
        user.setIsBlocked(userDTO.getIsBlocked());

        return user;
    }

    public UserDTO userToDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setCarNumber(user.getCarNumber());
        userDTO.setIsAdmin(user.getIsAdmin());
        userDTO.setIsBlocked(user.getIsBlocked());

        return userDTO;
    }
}
