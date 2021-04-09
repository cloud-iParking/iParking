package ro.ubb.cloud.iParking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ro.ubb.cloud.iParking.model.entities.Report;

@Component
public interface ReportRepository extends JpaRepository<Report, Integer> {
}
