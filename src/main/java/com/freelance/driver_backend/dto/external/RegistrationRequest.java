package com.freelance.driver_backend.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import java.util.Collections;
import java.util.List;

@Data
@Builder // Utiliser le pattern Builder est plus propre
public class RegistrationRequest {
    private String username;
    private String email;
    private String password;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("phone_number")
    private String phoneNumber;
    
    // Initialisé à une liste vide comme dans l'exemple de l'API
    @Builder.Default
    private List<Authority> authorities = Collections.emptyList();

    @Data
    public static class Authority {
        // Cette classe peut rester vide si on ne l'utilise pas, ou la supprimer si on envoie toujours une liste vide.
    }
}