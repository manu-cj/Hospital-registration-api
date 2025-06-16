package org.manu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChambreDTO {
    private UUID id;

    @NotBlank(message = "The number room is required")
    private String number;

    @NotNull(message = "The Availability is necessary ")
    private boolean available;
}
