package org.manu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.manu.dto.ChambreAssignementDTO;
import org.manu.dto.ChambreDTO;
import org.manu.services.ChambreAssignementService;
import org.manu.services.ChambreService;
import org.manu.services.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/chambre")
@RequiredArgsConstructor
public class ChambreController {
    private final ChambreAssignementService chambreAssignementService;
    private final ChambreService chambreService;
    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ChambreDTO dto) {
        ChambreDTO created = chambreService.create(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<ChambreDTO> chamber = chambreService.findAll();
        if (chamber.isEmpty()) {
            return  ResponseEntity.status(404).body("Chamber is empty");
        }
        return ResponseEntity.ok(chamber);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam UUID id) {
        boolean deleted = chambreService.deleteById(id);

        if (deleted) {
            return ResponseEntity.status(200).body("chamber deleted with success");
        } else {
            return ResponseEntity.status(404).body("chamber not found !");
        }
    }

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

    @GetMapping("/{available}")
    public ResponseEntity<?> getByAvailable(@PathVariable boolean available) {
        List<ChambreDTO> chamber = chambreService.findByAvailable(available);
        if (chamber == null) {
            return ResponseEntity.status(404).body("Not available rooms found");
        }
        return ResponseEntity.ok(chamber);
    }
}
