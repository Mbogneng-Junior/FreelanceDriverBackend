package com.freelance.driver_backend.service;

import com.freelance.driver_backend.dto.UserSessionContextDto;
import com.freelance.driver_backend.dto.external.OrganisationDto;
import com.freelance.driver_backend.model.ClientProfile;
import com.freelance.driver_backend.model.DriverProfile;
import com.freelance.driver_backend.repository.ClientProfileRepository;
import com.freelance.driver_backend.repository.DriverProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileService {

    private final DriverProfileRepository driverProfileRepository;
    private final ClientProfileRepository clientProfileRepository;
    // On n'a plus besoin de OrganisationService ici, car on ne l'appelle plus.
    // private final OrganisationService organisationService;

    public Mono<UserSessionContextDto> getUserSessionContext(UUID userId, String userBearerToken, String publicKey) {
        log.info("ProfileService: Recherche du contexte pour l'utilisateur ID: {}", userId);

        return driverProfileRepository.findByUserId(userId)
                .flatMap(this::buildContextForDriver)
                .switchIfEmpty(Mono.defer(() ->
                    clientProfileRepository.findByUserId(userId)
                            .flatMap(this::buildContextForClient)
                ))
                .switchIfEmpty(Mono.fromSupplier(() -> {
                    log.warn("ProfileService: Aucun profil (DRIVER ou CLIENT) trouvé pour l'utilisateur {}. Renvoi du statut NO_PROFILE.", userId);
                    return UserSessionContextDto.builder()
                            .userId(userId)
                            .role(UserSessionContextDto.UserRole.NO_PROFILE)
                            .build();
                }));
    }

    private Mono<UserSessionContextDto> buildContextForDriver(DriverProfile profile) {
        log.info("ProfileService: Profil DRIVER trouvé. Construction du contexte avec les données locales.");
        OrganisationDto orgDto = new OrganisationDto();
        orgDto.setOrganizationId(profile.getOrganisationId());
        orgDto.setLongName(profile.getFirstName() + "'s Business (Local)"); // On peut reconstruire un nom
        orgDto.setStatus("ACTIVE");

        return Mono.just(UserSessionContextDto.builder()
                .userId(profile.getUserId())
                .role(UserSessionContextDto.UserRole.DRIVER)
                .profile(profile)
                .organisation(orgDto)
                .build());
    }

    private Mono<UserSessionContextDto> buildContextForClient(ClientProfile profile) {
        log.info("ProfileService: Profil CLIENT trouvé. Construction du contexte avec les données locales.");
        OrganisationDto orgDto = new OrganisationDto();
        orgDto.setOrganizationId(profile.getOrganisationId());
        orgDto.setLongName(profile.getCompanyName() + " (Local)");
        orgDto.setStatus("ACTIVE");

        return Mono.just(UserSessionContextDto.builder()
                .userId(profile.getUserId())
                .role(UserSessionContextDto.UserRole.CLIENT)
                .profile(profile)
                .organisation(orgDto)
                .build());
    }
}