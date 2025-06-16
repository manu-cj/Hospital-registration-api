package org.manu.services;

import lombok.RequiredArgsConstructor;
import org.manu.dto.ChambreDTO;
import org.manu.mappers.ChambreMapper;
import org.manu.models.Chambre;
import org.manu.repositories.ChambreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChambreService {
    private final ChambreRepository chambreRepository;

    public ChambreDTO create(ChambreDTO dto) {
        Chambre chambre = ChambreMapper.toEntity(dto);
        Chambre saved = chambreRepository.save(chambre);
        return ChambreMapper.toDto(saved);
    }

    public List<ChambreDTO> findAll() {
        return chambreRepository.findAll().stream()
                .map(ChambreMapper::toDto)
                .toList();
    }


    public ChambreDTO findById(UUID id) {
        return chambreRepository.findById(id)
                .map(ChambreMapper::toDto)
                .orElse(null);
    }

    public ChambreDTO updateAvailability(UUID id, boolean availability) {
        Chambre chambre = chambreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chambre not found"));

        chambre.setAvailable(availability);
        Chambre updated = chambreRepository.save(chambre);
        return  ChambreMapper.toDto(updated);
    }
}
