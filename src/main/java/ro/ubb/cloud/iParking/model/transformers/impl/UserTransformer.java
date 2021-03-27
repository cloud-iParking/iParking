package ro.ubb.cloud.iParking.model.transformers.impl;


import org.springframework.stereotype.Component;
import ro.ubb.cloud.iParking.model.dto.UserDTO;
import ro.ubb.cloud.iParking.model.entities.User;
import ro.ubb.cloud.iParking.model.transformers.Transformer;

@Component
public class UserTransformer implements Transformer<User, UserDTO> {

    @Override
    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setCarNumber(user.getCarNumber());
        userDTO.setIsAdmin(user.getIsAdmin());
        userDTO.setIsBlocked(user.getIsBlocked());

        return userDTO;
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        User user = new User();

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setCarNumber(userDTO.getCarNumber());
        user.setIsAdmin(userDTO.getIsAdmin());
        user.setIsBlocked(userDTO.getIsBlocked());

        return user;
    }
}
