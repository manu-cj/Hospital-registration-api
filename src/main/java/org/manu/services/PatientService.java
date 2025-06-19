package org.manu.services;

import lombok.RequiredArgsConstructor;
import org.manu.dto.PatientDTO;
import org.manu.mappers.PatientMapper;
import org.manu.models.Chambre;
import org.manu.models.Patient;
import org.manu.repositories.ChambreRepository;
import org.manu.repositories.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository repository;
    private final ChambreRepository chambreRepository;

    @Transactional
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

    @Transactional
    public PatientDTO updateChambre(UUID patientId, UUID chambreId) {
        Patient patient = repository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Chambre chambre = chambreRepository.findById(chambreId)
                .orElseThrow(() -> new RuntimeException("Chambre not found"));

        patient.setChambre(chambre);
        Patient updated = repository.save(patient);
        return  PatientMapper.toDto(updated);
    }

    @Transactional
    public List<PatientDTO> getByFirstnameAndLastname(String firstname, String lastname) {

        return repository.findByPrenomStartingWithAndNomStartingWith(firstname, lastname).stream()
                .map(PatientMapper::toDto)
                .toList();
    }

    public PatientDTO getByEmail(String email) {
        return repository.findByEmail(email)
                .map(PatientMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }


}