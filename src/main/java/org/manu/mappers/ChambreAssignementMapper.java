package org.manu.mappers;

import org.manu.dto.ChambreAssignementDTO;
import org.manu.models.ChambreAssignement;

public class ChambreAssignementMapper {

    public static ChambreAssignementDTO toDto(ChambreAssignement entity) {
        if (entity == null) return null;
        return ChambreAssignementDTO.builder()
                .id(entity.getId())
                .patient(entity.getPatient() != null ? PatientMapper.toDto(entity.getPatient()) : null)
                .chambre(entity.getChambre() != null ? ChambreMapper.toDto(entity.getChambre()) : null)
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .build();
    }

    public static ChambreAssignement toEntity(ChambreAssignementDTO dto) {
        if (dto == null) return null;
        return ChambreAssignement.builder()
                .id(dto.getId())
                .patient(dto.getPatient() != null ? PatientMapper.toEntity(dto.getPatient()) : null)
                .chambre(dto.getChambre() != null ? ChambreMapper.toEntity(dto.getChambre()) : null)
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();
    }
}
