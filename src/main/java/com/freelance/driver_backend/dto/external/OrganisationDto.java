// Fichier : src/main/java/com/freelance/driver_backend/dto/external/OrganisationDto.java
package com.freelance.driver_backend.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.UUID;

@Data
public class OrganisationDto {
    @JsonProperty("organization_id")
    private UUID organizationId;
    @JsonProperty("long_name")
    private String longName;
    private String description;
    private String status;
    // Ajouter d'autres champs si nécessaire pour votre logique,
    // mais pour le moment, c'est suffisant.
}