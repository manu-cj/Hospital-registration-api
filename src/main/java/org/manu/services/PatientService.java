package org.manu.services;

import lombok.RequiredArgsConstructor;
import org.manu.dto.PatientDTO;
import org.manu.mappers.PatientMapper;
import org.manu.models.Chambre;
import org.manu.models.Patient;
import org.manu.repositories.ChambreRepository;
import org.manu.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository repository;
    private final ChambreRepository chambreRepository;

    public PatientDTO create(PatientDTO dto) {
        Patient patient = PatientMapper.toEntity(dto);
        Patient saved = repository.save(patient);
        return PatientMapper.toDto(saved);
    }

    public List<PatientDTO> findAll() {
        return repository.findAll().stream()
                .map(PatientMapper::toDto)
                .toList();
    }

    public PatientDTO findById(UUID id) {
        return repository.findById(id)
                .map(PatientMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    public PatientDTO updateChambre(UUID patientId, UUID chambreId) {
        Patient patient = repository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Chambre chambre = chambreRepository.findById(chambreId)
                .orElseThrow(() -> new RuntimeException("Chambre not found"));

        patient.setChambre(chambre);
        Patient updated = repository.save(patient);
        return  PatientMapper.toDto(updated);
    }

    public PatientDTO leaveChamber(UUID patientId, UUID chambreId) {
        Patient patient = repository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Chambre chambre = chambreRepository.findById(chambreId)
                .orElseThrow(() -> new RuntimeException("Chambre not found"));


    }
}