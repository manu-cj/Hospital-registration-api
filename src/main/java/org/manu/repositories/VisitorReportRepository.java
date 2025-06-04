package org.manu.repositories;

import org.manu.models.VisitorReport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class VisitorReportRepository {
    List<VisitorReport> visitorReportRepository = new ArrayList<>();

    /**
     * Save a new visitor report
     * @param visitorReport
     * @return
     */
    public VisitorReport save(VisitorReport visitorReport) {
        visitorReportRepository.add(visitorReport);
        return visitorReport;
    }
}
