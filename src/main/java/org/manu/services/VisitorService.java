package org.manu.services;

import org.manu.dto.VisitorDTO;
import org.manu.mappers.VisitorMapper;
import org.manu.models.Visitor;
import org.manu.repositories.VisitorRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VisitorService {
    private final VisitorRepository visitorRepository;

    public VisitorService(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    /**
     * Add data in repository with mapper
     * @param dto data
     * @return data in dto
     */
    public VisitorDTO createVisitor(VisitorDTO dto) {
        Visitor visitor = VisitorMapper.toModel(dto);

        if (visitor.getId() == null) {
            visitor.setId(UUID.randomUUID());
        }

        Visitor saved = visitorRepository.save(visitor);
        return VisitorMapper.toDTO(saved);
    }
}
