package ro.ubb.cloud.iParking.model.transformers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.cloud.iParking.model.dto.ReportDTO;
import ro.ubb.cloud.iParking.model.entities.Report;
import ro.ubb.cloud.iParking.model.transformers.Transformer;

@Component
public class ReportTransformer implements Transformer<Report, ReportDTO> {

    @Autowired
    ReservationTransformer reservationTransformer;

    @Override
    public ReportDTO toDTO(Report entity) {
        ReportDTO reportDTO = new ReportDTO();
        if (entity != null) {
            reportDTO.setId(entity.getId());
            reportDTO.setDescription(entity.getDescription());
            reportDTO.setReservation(reservationTransformer.toDTO(entity.getReservation()));
        }
        return reportDTO;
    }

    @Override
    public Report toEntity(ReportDTO dto) {
        Report report = new Report();
        if (dto != null) {
            report.setId(dto.getId());
            report.setDescription(dto.getDescription());
            report.setReservation(reservationTransformer.toEntity(dto.getReservation()));
        }
        return report;
    }
}
