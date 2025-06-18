package org.manu.services;

import lombok.RequiredArgsConstructor;
import org.manu.dto.ChambreAssignementDTO;
import org.manu.mappers.ChambreAssignementMapper;
import org.manu.models.ChambreAssignement;
import org.manu.repositories.ChambreAssignementRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChambreAssignementService {
    private final ChambreAssignementRepository chambreAssignementRepository;


    public ChambreAssignementDTO create(ChambreAssignementDTO dto) {
        boolean alreadyAssigned = chambreAssignementRepository
                .findByPatientId(dto.getPatient().getId())
                .isPresent();

        if (alreadyAssigned) {
            throw new RuntimeException("This patient already have a assignation.");
        }

        ChambreAssignement chambreAssignement = ChambreAssignementMapper.toEntity(dto);
        chambreAssignement.setStartDate(LocalDateTime.now());
        ChambreAssignement saved = chambreAssignementRepository.save(chambreAssignement);
        return ChambreAssignementMapper.toDto(saved);
    }


    //public ChambreAssignementDTO changeChambre(UUID patientId, ChambreAssignementDTO newAssignementDto) {
    //    ChambreAssignement active = chambreAssignementRepository.findByPatientIdAndEndDateIsNull(patientId)
    //            .orElseThrow(() -> new RuntimeException("active assignment not found for this patient."));

    //    active.setEndDate(LocalDateTime.now());
    //    chambreAssignementRepository.save(active);

    //    ChambreAssignement newAssignment = ChambreAssignementMapper.toEntity(newAssignementDto);
    //    ChambreAssignement saved = chambreAssignementRepository.save(newAssignment);
    //    return ChambreAssignementMapper.toDto(saved);
    //}

    public ChambreAssignementDTO leaveChamber(UUID patientId) {
        ChambreAssignement active = chambreAssignementRepository.findByPatientIdAndEndDateIsNull(patientId)
                .orElseThrow(() -> new RuntimeException("active assignment not found for this patient."));

        active.setEndDate(LocalDateTime.now());
        chambreAssignementRepository.save(active);

        return ChambreAssignementMapper.toDto(active);
    }

    public List<ChambreAssignementDTO> findAll() {
        return chambreAssignementRepository.findAll().stream()
                .map(ChambreAssignementMapper::toDto)
                .toList();
    }


}
