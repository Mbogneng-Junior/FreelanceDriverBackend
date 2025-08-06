package com.freelance.driver_backend.dto.onboarding;

import com.freelance.driver_backend.dto.external.ChatUserCreationResponse;
import lombok.Builder; // <-- Vérifie cet import
import lombok.Data;

@Data
@Builder // <-- C'est cette annotation qui crée la méthode builder()
public class ChatSessionInfo {
    private String token;
    private ChatUserCreationResponse userDetails;
}