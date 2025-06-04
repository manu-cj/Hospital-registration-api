package org.manu.controller;

import jakarta.validation.Valid;
import org.manu.dto.VisitorReportDTO;
import org.manu.services.VisitorReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/visitorReport")
public class VisitorReportController {
    private final VisitorReportService visitorReportService;

    public VisitorReportController(VisitorReportService visitorReportService) {
        this.visitorReportService = visitorReportService;
    }

    @PostMapping
    public ResponseEntity<VisitorReportDTO> createReport(@Valid @RequestBody VisitorReportDTO reportDTO) {
        VisitorReportDTO created = visitorReportService.createReport(reportDTO);
        return ResponseEntity.ok(created);
    }


}
