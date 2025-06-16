package org.manu.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "chambre_assignements")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChambreAssignement {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "chambre_id")
    private Chambre chambre;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
