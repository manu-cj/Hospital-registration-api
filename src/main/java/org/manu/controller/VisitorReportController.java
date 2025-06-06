package org.manu.controller;

import jakarta.validation.Valid;
import org.manu.dto.VisitorReportDTO;
import org.manu.services.VisitorReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitorReport")
public class VisitorReportController {
    private final VisitorReportService visitorReportService;

    public VisitorReportController(VisitorReportService visitorReportService) {
        this.visitorReportService = visitorReportService;
    }

    /**
     * Create a new report
     * @param reportDTO report data
     * @return response
     */
    @PostMapping
    public ResponseEntity<VisitorReportDTO> createReport(@Valid @RequestBody VisitorReportDTO reportDTO) {
        VisitorReportDTO created = visitorReportService.createReport(reportDTO);
        return ResponseEntity.ok(created);
    }

    /**
     *
     * @return all reports
     */
    @GetMapping
    public List<VisitorReportDTO> getAll() {
        return visitorReportService.getAll();
    }



}
