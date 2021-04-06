package ro.ubb.cloud.iParking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.ubb.cloud.iParking.model.dto.LoginDTO;
import ro.ubb.cloud.iParking.model.transformers.impl.LoginTransformer;
import ro.ubb.cloud.iParking.model.transformers.impl.UserTransformer;
import ro.ubb.cloud.iParking.repo.LoginRepository;
import java.util.ArrayList;
import java.util.Optional;
import ro.ubb.cloud.iParking.model.entities.User;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LoginTransformer loginTransformer;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = loginRepository.findByUsername(username);
        if(user.isPresent())
        {
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), passwordEncoder.encode(user.get().getPassword()),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public LoginDTO getLoginDTO(String username) {
        Optional<User> user = loginRepository.findByUsername(username);
        LoginDTO loginDTO = new LoginDTO();

        if(user.isPresent()) {
            return loginTransformer.toDTO(user.get());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
