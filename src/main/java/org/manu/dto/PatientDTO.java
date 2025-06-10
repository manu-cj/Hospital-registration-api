package org.manu.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDTO {
    private Long id;
    @NotBlank(message = "nom is required")
    private String nom;
    @NotBlank(message = "    private String prenom;\n is required")
    private String prenom;
    @NotBlank(message = "email is required")
    private String email;
}
