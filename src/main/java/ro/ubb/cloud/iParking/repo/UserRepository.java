package ro.ubb.cloud.iParking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ubb.cloud.iParking.model.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
