package org.manu.services;

import org.manu.dto.VisitorReportDTO;
import org.manu.mappers.VisitorReportMapper;
import org.manu.models.Visitor;
import org.manu.models.VisitorReport;
import org.manu.repositories.ReportRepository;
import org.manu.repositories.VisitorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VisitorReportService {
    private final ReportRepository reportRepository;
    private final VisitorRepository visitorRepository;

    public  VisitorReportService(ReportRepository reportRepository, VisitorRepository visitorRepository) {
        this.reportRepository = reportRepository;
        this.visitorRepository = visitorRepository;
    }

    /**
     * Add data in reportRepository and visitorRepository with mapper
     * @param dto data
     * @return data in dto
     */
    public VisitorReportDTO createReport(VisitorReportDTO dto) {
        VisitorReport report = VisitorReportMapper.toModel(dto);

        // add new visitor
        Visitor visitor = report.getVisitor();
        if (visitor.getId() == null) {
            visitor.setId(UUID.randomUUID());
        }
        Visitor savedVisitor = visitorRepository.save(visitor);

        //set visitor with id
        report.setVisitor(savedVisitor);

        //set id for report and add in repository
        if (report.getId() == null) {
            report.setId(UUID.randomUUID());
        }
        VisitorReport savedReport = reportRepository.save(report);

        return  VisitorReportMapper.toDTO(savedReport);
    }

    /**
     *
     * @return all reports
     */
    public List<VisitorReportDTO> getAll() {
        return reportRepository.findAll().stream()
                .map(VisitorReportMapper::toDTO)
                .collect(Collectors.toList());
    }

    public VisitorReportDTO getById(UUID id) {
        VisitorReport report = reportRepository.findById(id);
        if (report == null) {
            return null;
        }

        return VisitorReportMapper.toDTO(report);
    }

    public List<VisitorReportDTO> getByName(String lastname, String firstname) {
        return reportRepository.findByName(lastname, firstname).stream()
                .map(VisitorReportMapper::toDTO)
                .collect(Collectors.toList());
    }

    public boolean deleteById(UUID id) {
        if (!reportRepository.existsById(id)) {
            return false;
        }
         return reportRepository.deleteById(id);
    }





}
