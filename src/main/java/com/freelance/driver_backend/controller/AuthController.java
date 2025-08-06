package com.freelance.driver_backend.controller;

import com.freelance.driver_backend.dto.external.LoginRequest;
import com.freelance.driver_backend.dto.onboarding.OnboardingResponse;
import com.freelance.driver_backend.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final LoginService loginService;

    @PostMapping("/login")
    public Mono<ResponseEntity<OnboardingResponse>> login(@RequestBody LoginRequest loginRequest) {
        return loginService.loginAndGetContext(loginRequest)
                .map(ResponseEntity::ok)
                // Si le Mono est vide (login/profil non trouvé), renvoie une erreur 401 Unauthorized
                .defaultIfEmpty(ResponseEntity.status(401).build());
    }
}