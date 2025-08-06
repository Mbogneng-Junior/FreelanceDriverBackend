// Fichier : src/main/java/com/freelance/driver_backend/dto/external/LoginResponse.java
package com.freelance.driver_backend.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class LoginResponse {
    @JsonProperty("access_token")
    private AccessToken accessToken;
    private UserInfo user;
    private List<String> roles;
    private List<String> permissions;

    @Data
    public static class AccessToken {
        private String token;
        private String type;
        @JsonProperty("expire_in")
        private int expiresIn;
    }

    @Data
    public static class UserInfo {
        private UUID id;
        @JsonProperty("first_name")
        private String firstName;
        @JsonProperty("last_name")
        private String lastName;
        private String username;
        private String email;
        @JsonProperty("phone_number")
        private String phoneNumber;
        @JsonProperty("email_verified")
        private boolean emailVerified;
        @JsonProperty("phone_number_verified")
        private boolean phoneNumberVerified;
    }
}