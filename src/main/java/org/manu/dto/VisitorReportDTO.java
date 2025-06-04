package org.manu.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitorReportDTO {
    @NotNull(message = "Id is required !")
    private UUID id;
    @NotNull(message = "Le visiteur est requis")
    @Valid //
    private VisitorDTO visitor;
    @NotBlank(message = "Le nom du docteur est requis")
    private String doctorName;
    @NotBlank(message = "Le motif est requis")
    private String purpose;
    @NotNull(message = "La date est requise")
    private LocalDateTime timestamp;
}
