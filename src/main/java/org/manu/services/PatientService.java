package org.manu.services;

import lombok.RequiredArgsConstructor;
import org.manu.dto.PatientDTO;
import org.manu.mappers.PatientMapper;
import org.manu.models.Patient;
import org.manu.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository repository;

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
}