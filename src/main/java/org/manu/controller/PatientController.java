package org.manu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.manu.dto.ApiResponse;
import org.manu.dto.PatientDTO;
import org.manu.services.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PatientDTO dto) {
        try {
            PatientDTO created = service.create(dto);

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
        return ResponseEntity.ok(service.findAll());
    }
}
