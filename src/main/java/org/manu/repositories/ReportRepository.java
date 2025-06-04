package org.manu.repositories;

import org.manu.models.VisitorReport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReportRepository {
    List<VisitorReport> reportRepository = new ArrayList<>();

    /**
     * Save a new visitor report
     * @param visitorReport
     * @return
     */
    public VisitorReport save(VisitorReport visitorReport) {

        reportRepository.add(visitorReport);
        return visitorReport;
    }
}
