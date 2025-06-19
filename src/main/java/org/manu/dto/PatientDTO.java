package org.manu.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDTO {
    private UUID id;
    @NotBlank(message = "nom is required")
    private String nom;
    @NotBlank(message = "prenom is required")
    private String prenom;
    @NotBlank(message = "email is required")
    private String email;

    private ChambreDTO chambre;
}
