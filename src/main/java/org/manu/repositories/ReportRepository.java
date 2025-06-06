package org.manu.repositories;

import org.manu.models.VisitorReport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ReportRepository {
    List<VisitorReport> reportRepository = new ArrayList<>();

    /**
     * Save a new visitor report
     * @param visitorReport data
     * @return visitorReport
     */
    public VisitorReport save(VisitorReport visitorReport) {
        reportRepository.add(visitorReport);
        return visitorReport;
    }

    /**
     *
     * @return repository with all reports
     */
    public List<VisitorReport> findAll() {
        return reportRepository;
    }


    /**
     * Verify if report exist
     * @param id data
     * @return boolean
     */
    public boolean existsById(UUID id) {
        return reportRepository.stream()
                .anyMatch(report -> report.getId().equals(id));
    }


    public VisitorReport findById(UUID id) {
        return reportRepository.stream()
                .filter(report -> report.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Searches for visitor reports matching a given first name and last name.
     * The results also include cases where the first name and last name are swapped.
     * @param lastname the visitor's last name
     * @param firstname the visitor's first name
     * @return the list of reports matching the criteria
     */
    public List<VisitorReport> findByName(String lastname, String firstname) {
        return reportRepository.stream()
                .filter(report ->
                        (report.getVisitor().getFirstname().startsWith(firstname)
                                && report.getVisitor().getLastname().startsWith(lastname))
                                || (report.getVisitor().getFirstname().startsWith(lastname)
                                && report.getVisitor().getLastname().startsWith(firstname))
                )
                .toList();
    }

    public boolean deleteById(UUID id) {
        return reportRepository.removeIf(report -> report.getId().equals(id));
    }
}
