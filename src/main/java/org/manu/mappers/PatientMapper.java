package org.manu.mappers;

import org.manu.dto.PatientDTO;
import org.manu.models.Patient;

public class PatientMapper {

    /**
     * Converts a Patient entity to a PatientDTO.
     * @param entity the Patient entity to convert
     * @return the corresponding PatientDTO
     */
    public static PatientDTO toDto(Patient entity) {
        return PatientDTO.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .prenom(entity.getPrenom())
                .email(entity.getEmail())
                .chambre(entity.getChambre() != null ? ChambreMapper.toDto(entity.getChambre()) : null)
                .build();
    }

    /**
     * Converts a PatientDTO to a Patient entity.
     * @param dto the PatientDTO to convert
     * @return the corresponding Patient entity
     */
    public static Patient toEntity(PatientDTO dto) {
        return Patient.builder()
                .id(dto.getId())
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .email(dto.getEmail())
                .chambre(dto.getChambre() != null ? ChambreMapper.toEntity(dto.getChambre()) : null)
                .build();
    }
}
