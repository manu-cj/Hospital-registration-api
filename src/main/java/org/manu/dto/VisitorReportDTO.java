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
    private UUID id;
    @NotNull(message = "The visitor is required")
    @Valid //
    private VisitorDTO visitor;
    @NotBlank(message = "The doctor name is required")
    private String doctorName;
    @NotBlank(message = "The motif is required")
    private String purpose;
    @NotNull(message = "The date is required")
    private LocalDateTime timestamp;
}
