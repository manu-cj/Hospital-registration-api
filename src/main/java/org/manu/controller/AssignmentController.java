package org.manu.controller;

import lombok.RequiredArgsConstructor;
import org.manu.dto.ChambreAssignementDTO;
import org.manu.services.ChambreAssignementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/assignment")
@RequiredArgsConstructor
public class AssignmentController {

    private final ChambreAssignementService chambreAssignementService;

    @GetMapping("/assignment")
    public ResponseEntity<?> getAllAssignment() {
        List<ChambreAssignementDTO> assignments = chambreAssignementService.findAll();
        if (assignments.isEmpty()) {
            return ResponseEntity.status(404).body("Assignment is empty");
        }
        return ResponseEntity.ok(assignments);
    }

    @GetMapping("/{number}")
    public ResponseEntity<?> getByNumber(@PathVariable String number) {
        List<ChambreAssignementDTO> chamber = chambreAssignementService.findByNumberChamber(number);
        if (chamber == null) {
            return ResponseEntity.status(404).body("Chamber not found");
        }
        return  ResponseEntity.ok(chamber);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<?> getByPatient(@PathVariable UUID id) {
        List<ChambreAssignementDTO> chamber = chambreAssignementService.findByPatient(id);
        if (chamber == null) {
            return ResponseEntity.status(404).body("We don't have a assignment with this patient");
        }
        return ResponseEntity.ok(chamber);
    }
}
