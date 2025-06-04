package org.manu.mapper;

import org.manu.dto.VisitorDTO;
import org.manu.models.Visitor;

public class VisitorMapper {

    public static VisitorDTO toDTO(Visitor visitor) {
        return VisitorDTO.builder()
                .id(visitor.getId())
                .firstname(visitor.getFirstname())
                .lastname(visitor.getLastname())
                .build();
    }

    public static Visitor toModel(VisitorDTO dto) {
        if (dto == null) return null;
        Visitor visitor = new Visitor(dto.getId(), dto.getFirstname(), dto.getLastname());
        return visitor;
    }
}
