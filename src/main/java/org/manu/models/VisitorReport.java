package org.manu.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "visitorReport")
public class VisitorReport {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    private Visitor visitor;
    private String doctorName;
    private String purpose;
    private LocalDateTime timestamp;
}