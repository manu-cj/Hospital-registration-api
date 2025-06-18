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
    public List<ChambreDTO> getAll() {
        return chambreService.findAll();
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam UUID id) {
        boolean deleted = chambreService.deleteById(id);

        if (deleted) {
            return ResponseEntity.status(201).body("chamber deleted with success");
        } else {
            return ResponseEntity.status(404).body("chamber not found !");
        }
    }
}
