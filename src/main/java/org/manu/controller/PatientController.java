package org.manu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.manu.dto.ApiResponse;
import org.manu.dto.ChambreAssignementDTO;
import org.manu.dto.ChambreDTO;
import org.manu.dto.PatientDTO;
import org.manu.models.Chambre;
import org.manu.services.ChambreAssignementService;
import org.manu.services.ChambreService;
import org.manu.services.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final ChambreAssignementService chambreAssignementService;
    private final ChambreService chambreService;
    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PatientDTO dto) {
        try {
            ChambreDTO chambre = chambreService.findById(dto.getChambre().getId());
            if (!chambre.isAvailable()) {
                throw new IllegalStateException("This chamber is not disponible.");
            }

            PatientDTO created = patientService.create(dto);

            chambreService.updateAvailability(chambre.getId(), false);

            ChambreAssignementDTO assignmentDTO = new ChambreAssignementDTO();
            assignmentDTO.setPatient(created);
            assignmentDTO.setChambre(chambre);

            ChambreAssignementDTO createdAssignment = chambreAssignementService.create(assignmentDTO);

            return ResponseEntity
                    .created(URI.create("/patients/" + created.getId()))
                    .body(created);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResponse("Invalid data  : " + e.getMessage()));
        }
        catch (IllegalStateException e) {
            return ResponseEntity
                    .status(409)
                    .body(new ApiResponse("Conflict  : " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body(new ApiResponse("Internal error : " + e.getMessage()));
        }

    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAll() {
        return ResponseEntity.ok(patientService.findAll());
    }
}
