package org.manu.dto;


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
public class ChambreAssignementDTO {
    private UUID id;

   private PatientDTO patient;

   private ChambreDTO chambre;

    @NotNull(message = "The start date is required")
    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
