package ro.ubb.cloud.iParking.model.transformers.impl;

import org.springframework.stereotype.Component;
import ro.ubb.cloud.iParking.model.dto.LoginDTO;
import ro.ubb.cloud.iParking.model.entities.User;
import ro.ubb.cloud.iParking.model.transformers.Transformer;

@Component
public class LoginTransformer  implements Transformer<User, LoginDTO> {

    @Override
    public LoginDTO toDTO(User user) {
        LoginDTO loginDTO = new LoginDTO();

        loginDTO.setId(user.getId());
        loginDTO.setFirstName(user.getFirstName());
        loginDTO.setLastName(user.getLastName());
        loginDTO.setUsername(user.getUsername());
        loginDTO.setPassword(user.getPassword());
        loginDTO.setEmail(user.getEmail());
        loginDTO.setPhone(user.getPhone());
        loginDTO.setCarNumber(user.getCarNumber());
        loginDTO.setIsAdmin(user.getIsAdmin());
        loginDTO.setIsBlocked(user.getIsBlocked());

        return loginDTO;
    }

    @Override
    public User toEntity(LoginDTO dto) {
        // TO DO
        return null;
    }
}
