package ro.ubb.cloud.iParking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.cloud.iParking.model.dto.LoginDTO;
import ro.ubb.cloud.iParking.model.dto.UserDTO;
import ro.ubb.cloud.iParking.model.entities.User;
import ro.ubb.cloud.iParking.model.transformers.impl.UserTransformer;
import ro.ubb.cloud.iParking.repo.LoginRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private UserTransformer userTransformer;

    public UserDTO login(LoginDTO loginDTO) {
        Optional<User> user = loginRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
        if (user.isPresent()) {
            return userTransformer.toDTO(user.get());
        }
        // TODO: 27.03.2021 handle exception after implementing an Exception mechanism
        throw new EntityNotFoundException();
    }
}
