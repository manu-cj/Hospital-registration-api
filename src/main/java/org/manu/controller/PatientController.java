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
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

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
            // Retrieve the room by its ID
            ChambreDTO chambre = chambreService.findById(dto.getChambre().getId());

            // Check if the room is available
            if (!chambre.isAvailable()) {
                throw new IllegalStateException("This chamber is not available.");
            } else {
                // Create the patient
                PatientDTO created = patientService.create(dto);

                // Update the room's availability to false in database
                chambreService.updateAvailability(chambre.getId(), false);

                // Update the local chambre object and set it to the created patient
                chambre.setAvailable(false);
                created.setChambre(chambre);


                // Prepare the room assignment DTO
                ChambreAssignementDTO assignmentDTO = new ChambreAssignementDTO();
                assignmentDTO.setPatient(created);
                assignmentDTO.setChambre(chambre);



                // Create the room assignment
                ChambreAssignementDTO createdAssignment = chambreAssignementService.create(assignmentDTO);

                // Return a response with the created assignment and location header
                return ResponseEntity
                        .created(URI.create("/patients/" + created.getId()))
                        .body(createdAssignment);
            }
        }
        catch (IllegalArgumentException e) {
            // Handle invalid data
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResponse("Invalid data  : " + e.getMessage()));
        }
        catch (IllegalStateException e) {
            // Handle conflict (e.g., room not available)
            return ResponseEntity
                    .status(409)
                    .body(new ApiResponse("Conflict  : " + e.getMessage()));
        } catch (Exception e) {
            // Handle unexpected errors
            return ResponseEntity
                    .status(500)
                    .body(new ApiResponse("Internal error  : " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAll() {
        return ResponseEntity.ok(patientService.findAll());
    }

    @PutMapping("/change")
    public ResponseEntity<?> Change(@RequestParam UUID patientId, @RequestParam UUID newChambreId) {
        try {
            PatientDTO patient = patientService.findById(patientId);
            ChambreDTO oldChamber = patient.getChambre();
            ChambreDTO newChamber = chambreService.findById(newChambreId);

            if (!newChamber.isAvailable()) {
                throw new IllegalStateException("The new chamber is not available");
            }

            // Update availability for old chamber and new chamber
            chambreService.updateAvailability(oldChamber.getId(), true);
            chambreService.updateAvailability(newChamber.getId(), false);

            // update patient
            newChamber.setAvailable(false);
            patient.setChambre(newChamber);
            PatientDTO updatedPatient = patientService.updateChambre(patientId, newChambreId);

            // leave chamber
            chambreAssignementService.leaveChamber(patientId);

            // create new assignment
            ChambreAssignementDTO assignmentDTO = new ChambreAssignementDTO();
            assignmentDTO.setChambre(newChamber);
            assignmentDTO.setPatient(patient);

            // create new assignment
            chambreAssignementService.create(assignmentDTO);

            return ResponseEntity.ok(updatedPatient);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Invalid data  : " + e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body(new ApiResponse("Conflict  : " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse("Internal error  : " + e.getMessage()));
        }
    }

    @GetMapping("search")
    public ResponseEntity<?> getPatientByFirstnameAndLastname(@RequestParam String firstname, @RequestParam String lastname) {
        List<PatientDTO> patients = patientService.getByFirstnameAndLastname(firstname.toLowerCase(), lastname.toLowerCase());
        if (patients == null) {
            return ResponseEntity.status(404).body("Not patient with this firstname and lastname.");
        }
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable String email) {
        PatientDTO patient = patientService.getByEmail(email);
        if (patient == null) {
            return ResponseEntity.status(404).body("Patient not found.");
        }
        return ResponseEntity.ok(patient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        PatientDTO patient = patientService.findById(id);
        if (patient == null) {
            return ResponseEntity.status(404).body("Patient not found");
        }
        return ResponseEntity.ok(patient);
    }
}
