package ro.ubb.cloud.iParking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.cloud.iParking.model.entities.Street;

import java.util.List;

public interface StreetRepository extends JpaRepository<Street, Integer> {
    List<Street> findAllByName(String streetName);
}
