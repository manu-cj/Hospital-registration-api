package org.manu.controller;

import jakarta.validation.Valid;
import org.manu.dto.VisitorReportDTO;
import org.manu.services.VisitorReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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

    /**
     * Retrieves a report by its unique identifier
     * @param id report identifier
     * @return the corresponding report
     */
    @GetMapping("/{id}")
    public ResponseEntity<VisitorReportDTO> getById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(visitorReportService.getById(id));
    }

    /**
    * Retrieves the reports of a visitor by last name and first name
    * @param lastname visitor's last name
    * @param firstname visitor's first name
    * @return list of matching reports
    */
    @GetMapping("/visitor/{lastname}/{firstname}")
    public List<VisitorReportDTO> getByName(
            @PathVariable("lastname") String lastname,
            @PathVariable("firstname") String firstname) {
        return visitorReportService.getByName(lastname, firstname);
        }


    // Deletes a visitor report by its unique identifier
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteVisitor(@PathVariable("id") UUID id) {
        boolean deleted = visitorReportService.deleteById(id);
        if (deleted) {
            return ResponseEntity.ok(Map.of("message", "Rapport supprimé avec succès"));
        } else {
            return ResponseEntity.status(404).body(Map.of("message", "Rapport non trouvé"));
        }
    }



}
