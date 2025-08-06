package com.freelance.driver_backend.dto.external;

import lombok.Data;


@Data
public class ChatUserCreationResponse {
    private String id;
    private String projectId;
    private String externalId;
    private String avatar;
    private String displayName;
    private String email;
    private String phoneNumber;
    private String login;
    private String username;
    private boolean online;
    private boolean enabled;
    // On peut omettre les autres champs pour la simplicité
}