package org.manu.mappers;

import org.manu.dto.ChambreDTO;
import org.manu.models.Chambre;

import java.util.UUID;

public class ChambreMapper {

    /**
     * Converts a Chambre entity to a ChambreDTO.
     * @param entity the Chambre entity to convert
     * @return the corresponding ChambreDTO
     */
    public static ChambreDTO toDto(Chambre entity) {
        return ChambreDTO.builder()
                .id(entity.getId())
                .number(entity.getNumber())
                .available(entity.isAvailable())
                .build();
    }

    /**
     * Converts a ChambreDTO to a Chambre entity.
     * @param dto the ChambreDTO to convert
     * @return the corresponding Chambre entity
     */
    public static Chambre toEntity(ChambreDTO dto) {
        return Chambre.builder()
                .id(dto.getId())
                .number(dto.getNumber())
                .available(dto.isAvailable())
                .build();
    }



}
