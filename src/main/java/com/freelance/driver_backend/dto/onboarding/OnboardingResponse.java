package com.freelance.driver_backend.dto.onboarding;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OnboardingResponse {
    private String token;
    private Object profile;
    private ChatSessionInfo chatSession; // <-- AJOUTE CETTE LIGNE
}