package com.freelance.driver_backend.controller;

import com.freelance.driver_backend.dto.UserSessionContextDto;
import com.freelance.driver_backend.service.ProfileService;
import com.freelance.driver_backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;
    private final String publicKey;

    public ProfileController(ProfileService profileService,
                             @Value("${freelancedriver.api.public-key}") String publicKey) {
        this.profileService = profileService;
        this.publicKey = publicKey;
    }

    @GetMapping("/me")
    public Mono<ResponseEntity<UserSessionContextDto>> getMyProfile(
            @AuthenticationPrincipal Mono<Jwt> jwtMono,
            @RequestHeader("Authorization") String authorizationHeader) {

        return jwtMono
                .flatMap(jwt -> {
                    UUID userId = JwtUtil.getUserIdFromToken(jwt);
                    return profileService.getUserSessionContext(userId, authorizationHeader, publicKey);
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}