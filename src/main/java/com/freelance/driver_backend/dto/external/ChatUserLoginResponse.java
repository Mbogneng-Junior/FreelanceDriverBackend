package com.freelance.driver_backend.dto.external;

import lombok.Data;

@Data
public class ChatUserLoginResponse {
    private ChatUserCreationResponse data;
    private String token;
}