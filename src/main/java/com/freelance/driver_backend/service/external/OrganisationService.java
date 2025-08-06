package com.freelance.driver_backend.service.external;

import com.freelance.driver_backend.dto.external.OrganisationCreationRequest;
import com.freelance.driver_backend.dto.external.OrganisationDto;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.UUID;

public interface OrganisationService {
    // La signature est maintenant Mono<OrganisationDto> pour refléter la réponse de l'API
    Mono<OrganisationDto> createOrganisation(OrganisationCreationRequest request, String bearerToken, String publicKey);

    Mono<List<OrganisationDto>> getUserOrganisations(String userBearerToken, String publicKey);
    Mono<OrganisationDto> getOrganisationById(UUID organisationId, String userBearerToken, String publicKey);
}