package com.freelance.driver_backend.service.external.mock;

import com.freelance.driver_backend.dto.external.OrganisationCreationRequest;
import com.freelance.driver_backend.dto.external.OrganisationDto;
import com.freelance.driver_backend.model.mock.MockOrganisation;
import com.freelance.driver_backend.repository.mock.MockOrganisationRepository;
import com.freelance.driver_backend.service.external.OrganisationService;
import com.freelance.driver_backend.util.JwtUtil; // Utilitaire pour décoder le token
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
@Profile("dev-resource-mock") // Profil correct
@RequiredArgsConstructor
@Slf4j
public class MockOrganisationServiceImpl implements OrganisationService {

    private final MockOrganisationRepository mockOrganisationRepository;
    private final ReactiveJwtDecoder jwtDecoder; // On injecte le décodeur de JWT

    @Override
    public Mono<OrganisationDto> createOrganisation(OrganisationCreationRequest request, String bearerToken, String publicKey) {
        log.warn("[MOCK-ORG] Creating organisation '{}' in local DB.", request.getLongName());

        // On décode le VRAI token pour obtenir le VRAI userId
        return decodeToken(bearerToken)
            .flatMap(jwt -> {
                UUID ownerId = JwtUtil.getUserIdFromToken(jwt);
                log.info("[MOCK-ORG] Extracted real userId {} from token for new organisation.", ownerId);

                MockOrganisation newOrg = new MockOrganisation();
                newOrg.setOrganizationId(UUID.randomUUID());
                newOrg.setLongName(request.getLongName());
                newOrg.setDescription(request.getDescription());
                newOrg.setOwnerUserId(ownerId); // On utilise le vrai ID !

                return mockOrganisationRepository.save(newOrg).map(this::mapToDto);
            });
    }

    @Override
    public Mono<List<OrganisationDto>> getUserOrganisations(String userBearerToken, String publicKey) {
        log.warn("[MOCK-ORG] Getting user organisations from local DB using real user token.");
        
        return decodeToken(userBearerToken)
            .flatMap(jwt -> {
                UUID ownerId = JwtUtil.getUserIdFromToken(jwt);
                log.info("[MOCK-ORG] Finding organisations for real userId {}.", ownerId);
                return mockOrganisationRepository.findByOwnerUserId(ownerId)
                    .map(this::mapToDto)
                    .collectList();
            });
    }

    @Override
    public Mono<OrganisationDto> getOrganisationById(UUID organisationId, String userBearerToken, String publicKey) {
         return mockOrganisationRepository.findById(organisationId).map(this::mapToDto);
    }
    
    private OrganisationDto mapToDto(MockOrganisation org) {
        OrganisationDto dto = new OrganisationDto();
        dto.setOrganizationId(org.getOrganizationId());
        dto.setLongName(org.getLongName());
        dto.setDescription(org.getDescription());
        dto.setStatus("ACTIVE"); // Simule un statut actif
        return dto;
    }

    // Méthode utilitaire pour décoder le token JWT
    private Mono<Jwt> decodeToken(String bearerToken) {
        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            return Mono.error(new IllegalArgumentException("Invalid Authorization header format"));
        }
        String token = bearerToken.substring(7);
        return jwtDecoder.decode(token)
            .doOnError(JwtException.class, e -> log.error("Failed to decode JWT token", e));
    }
}