package ro.ubb.cloud.iParking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ro.ubb.cloud.iParking.model.User;

@Repository
@Component
public interface UserRepository extends JpaRepository<User,Integer> {
}
