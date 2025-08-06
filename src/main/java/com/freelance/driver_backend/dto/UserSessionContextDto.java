package com.freelance.driver_backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.freelance.driver_backend.dto.external.OrganisationDto;
import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder // Le Builder sera très pratique pour construire cet objet complexe
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserSessionContextDto {

    private UUID userId;
    private UserRole role;
    private Object profile; // Le profil spécifique (DriverProfile ou ClientProfile)
    private OrganisationDto organisation; // L'objet Organisation enrichi

    public enum UserRole {
        DRIVER,
        CLIENT,
        NO_PROFILE
    }
}