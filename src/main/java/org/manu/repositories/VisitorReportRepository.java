package org.manu.repositories;

import org.manu.models.VisitorReport;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VisitorReportRepository {
    List<VisitorReport> visitorReportRepository = new ArrayList<>();

    /**
     * Save a new visitor report
     * @param visitorReport
     * @return
     */
    public VisitorReport save(VisitorReport visitorReport) {
        if (visitorReport.getId() == null) {
            visitorReport.setId(UUID.randomUUID());
        }
        visitorReportRepository.add(visitorReport);
        return visitorReport;
    }
}
