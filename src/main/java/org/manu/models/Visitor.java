package org.manu.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Visitor {
    private UUID id;
    private String firstname;
    private String Lastname;
}