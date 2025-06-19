package org.manu.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {

    @Id
    @GeneratedValue
    private UUID id;

    private String nom;
    private String prenom;

    @Column(unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "chambre_id")
    private Chambre chambre;
}

