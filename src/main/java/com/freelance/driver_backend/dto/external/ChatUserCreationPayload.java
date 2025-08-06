package com.freelance.driver_backend.dto.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatUserCreationPayload {
    private String projectId;
    private String externalId;
    private String avatar;
    private String displayName;
    private String email;
    private String phoneNumber;
    private String login;
    private String secret;
    private Map<String, String> customJson;
}