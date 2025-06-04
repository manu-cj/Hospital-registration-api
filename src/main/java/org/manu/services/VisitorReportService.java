package org.manu.services;

import org.manu.dto.VisitorReportDTO;
import org.manu.mappers.VisitorReportMapper;
import org.manu.models.VisitorReport;
import org.manu.repositories.VisitorReportRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VisitorReportService {
    private final VisitorReportRepository visitorReportRepository;

    public  VisitorReportService(VisitorReportRepository visitorReportRepository) {
        this.visitorReportRepository = visitorReportRepository;
    }

    /**
     * Add data in repository with mapper
     * @param dto
     * @return
     */
    public VisitorReportDTO createReport(VisitorReportDTO dto) {

        VisitorReport report = VisitorReportMapper.toModel(dto);

        if (report.getId() == null) {
            report.setId(UUID.randomUUID());
        }

        VisitorReport saved = visitorReportRepository.save(report);
        return  VisitorReportMapper.toDTO(saved);
    }


}
