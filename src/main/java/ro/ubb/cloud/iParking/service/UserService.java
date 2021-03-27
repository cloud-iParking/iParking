package ro.ubb.cloud.iParking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.cloud.iParking.model.dto.UserDTO;
import ro.ubb.cloud.iParking.model.entities.User;
import ro.ubb.cloud.iParking.model.transformers.impl.UserTransformer;
import ro.ubb.cloud.iParking.repo.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTransformer userTransformer;

    public UserDTO save(UserDTO userDTO) {
        User user = userRepository.save(userTransformer.toEntity(userDTO));
        return userTransformer.toDTO(user);
    }
}
