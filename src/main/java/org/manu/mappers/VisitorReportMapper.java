package org.manu.mappers;

import org.manu.dto.VisitorReportDTO;
import org.manu.models.VisitorReport;

public class VisitorReportMapper {

    public static VisitorReportDTO toDTO(VisitorReport report) {
        return VisitorReportDTO.builder()
                .id(report.getId())
                .visitor(VisitorMapper.toDTO(report.getVisitor()))
                .doctorName(report.getDoctorName())
                .purpose(report.getPurpose())
                .timestamp(report.getTimestamp())
                .build();
    }


    public static VisitorReport toModel(VisitorReportDTO dto) {
        return new VisitorReport(
                dto.getId(),
                VisitorMapper.toModel(dto.getVisitor()),
                dto.getDoctorName(),
                dto.getPurpose(),
                dto.getTimestamp()
        );
    }

}
