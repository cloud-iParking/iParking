package ro.ubb.cloud.iParking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ro.ubb.cloud.iParking.model.User;
import ro.ubb.cloud.iParking.repo.UserRepository;
import ro.ubb.cloud.iParking.service.dto.DtoMapping;
import ro.ubb.cloud.iParking.service.dto.UserDTO;

@Service
@Component
public class UserService {

    private final UserRepository userRepository;

    private final DtoMapping dtoMapping;

    @Autowired
    public UserService(UserRepository userRepository, DtoMapping dtoMapping) {
        this.userRepository = userRepository;
        this.dtoMapping = dtoMapping;
    }

    public UserDTO save(UserDTO userDTO) {
        User user = userRepository.save(dtoMapping.dtoToUser(userDTO));
        return dtoMapping.userToDTO(user);
    }
}
