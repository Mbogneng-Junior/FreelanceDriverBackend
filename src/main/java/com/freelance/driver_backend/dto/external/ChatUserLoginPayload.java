package com.freelance.driver_backend.dto.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatUserLoginPayload {
    private String projectId;
    private String login;
    private String secret;
}
