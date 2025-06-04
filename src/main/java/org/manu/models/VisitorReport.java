package org.manu.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor

public class VisitorReport {
    private UUID id;
    private Visitor visitor;
    private String doctorName;
    private String purpose;
    private LocalDateTime timestamp;
}