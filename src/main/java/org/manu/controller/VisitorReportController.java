package org.manu.controller;

import jakarta.validation.Valid;
import org.manu.dto.VisitorReportDTO;
import org.manu.services.VisitorReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<VisitorReportDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(visitorReportService.getById(id));
    }


    @GetMapping("/visitor/{lastname}/{firstname}")
    public List<VisitorReportDTO> getByName(
            @PathVariable("lastname") String lastname,
            @PathVariable("firstname") String firstname) {
        return visitorReportService.getByName(lastname, firstname);
    }



}
