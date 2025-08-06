

/*package com.freelance.driver_backend.controller;

import com.freelance.driver_backend.model.ClientProfile;
import com.freelance.driver_backend.model.Resource;
import com.freelance.driver_backend.model.ResourceKey;
import com.freelance.driver_backend.service.ProfileService;
import com.freelance.driver_backend.service.resource.ResourceService;
import com.freelance.driver_backend.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.math.BigDecimal;
import java.time.Instant;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/announcements")
@RequiredArgsConstructor
@Slf4j
public class AnnouncementController {

    private final ResourceService resourceService;
    private final ProfileService profileService;
    

    private static final String ANNOUNCEMENT_CATEGORY_ID = "c1a5b4e0-1234-5678-9abc-def012345678";

    @GetMapping
    public Flux<Resource> getPublishedAnnouncements() {
        log.info("Controller: Requête pour récupérer les annonces publiées.");
        // Le contrôleur fait maintenant confiance au service pour renvoyer les données enrichies.
        return resourceService.getResourcesByCategory(ANNOUNCEMENT_CATEGORY_ID)
                .filter(resource -> "AVAILABLE".equalsIgnoreCase(resource.getState()));
    }
    

    @GetMapping("/my-announcements")
    public Flux<Resource> getMyAnnouncements(
            @AuthenticationPrincipal Mono<Jwt> jwtMono,
            @RequestHeader("Authorization") String authorizationHeader) {
        
        return jwtMono
            .flatMap(jwt -> profileService.getUserSessionContext(JwtUtil.getUserIdFromToken(jwt), authorizationHeader, null))
            .flatMapMany(userContext -> {
                if (userContext.getOrganisation() == null) {
                    return Flux.error(new IllegalStateException("User does not have a valid organisation."));
                }
                UUID organizationId = userContext.getOrganisation().getOrganizationId();
                log.info("Récupération des annonces pour l'organisation {}", organizationId);
                return resourceService.getResourcesByOrganisationAndCategory(organizationId, ANNOUNCEMENT_CATEGORY_ID);
            });
    }

    @PostMapping
    public Mono<ResponseEntity<Resource>> createAnnouncement(
            @RequestBody Map<String, Object> payload,
            @AuthenticationPrincipal Mono<Jwt> jwtMono,
            @RequestHeader("Authorization") String authorizationHeader) {

        return jwtMono
            .flatMap(jwt -> profileService.getUserSessionContext(JwtUtil.getUserIdFromToken(jwt), authorizationHeader, null))
            .flatMap(userContext -> {
                if (userContext.getOrganisation() == null || !(userContext.getProfile() instanceof ClientProfile)) {
                    return Mono.error(new IllegalStateException("Cannot create announcement: user context is invalid or not a client."));
                }
                
                // ==============================================================================
                //                         LA SYNTAXE EST CORRIGÉE ICI
                // ==============================================================================
                
                ClientProfile clientProfile = (ClientProfile) userContext.getProfile();
                
                log.info("Mapping manually the received payload to a Resource object.");
                Resource announcement = new Resource();

                announcement.setName((String) payload.get("name"));
                announcement.setStorageCondition((String) payload.get("storageCondition"));
                announcement.setLongDescription((String) payload.get("longDescription"));
                announcement.setShortDescription((String) payload.get("shortDescription"));
                announcement.setSkuCode((String) payload.get("skuCode"));
                announcement.setState((String) payload.get("state"));
                
                Object basePriceObj = payload.get("basePrice");
                if (basePriceObj instanceof Number) {
                    announcement.setBasePrice(new BigDecimal(((Number) basePriceObj).toString()));
                }

                Object expiresAtObj = payload.get("expiresAt");
                if (expiresAtObj instanceof String) {
                    announcement.setExpiresAt(Instant.parse((String) expiresAtObj));
                }

                @SuppressWarnings("unchecked")
                Map<String, String> receivedMetadata = (Map<String, String>) payload.getOrDefault("metadata", new HashMap<String, String>());
                
                Map<String, String> finalMetadata = new HashMap<>(receivedMetadata);
                finalMetadata.put("clientPhoneNumber", clientProfile.getPhoneNumber());
                announcement.setMetadata(finalMetadata);

                UUID organizationId = userContext.getOrganisation().getOrganizationId();
                announcement.setOrganizationId(organizationId); // <-- C'est ici qu'on assigne l'ID
                announcement.setCategoryId(ANNOUNCEMENT_CATEGORY_ID);
                announcement.setResourceId(UUID.randomUUID());

                log.info("Resource object created manually: {}", announcement);
                
                return resourceService.createResource(announcement);
            })
            .map(created -> new ResponseEntity<>(created, HttpStatus.CREATED));
    }
    
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Resource>> updateAnnouncement(
            @PathVariable("id") UUID resourceId,
            @RequestBody Resource announcement, // Pour la mise à jour, le mapping auto est souvent suffisant
            @AuthenticationPrincipal Mono<Jwt> jwtMono,
            @RequestHeader("Authorization") String authorizationHeader) {

        return jwtMono
            .flatMap(jwt -> profileService.getUserSessionContext(JwtUtil.getUserIdFromToken(jwt), authorizationHeader, null))
            .flatMap(userContext -> {
                if (userContext.getOrganisation() == null) {
                    return Mono.error(new IllegalStateException("Cannot update announcement: user has no organisation."));
                }
                ResourceKey key = new ResourceKey();
                key.setOrganizationId(userContext.getOrganisation().getOrganizationId());
                key.setCategoryId(ANNOUNCEMENT_CATEGORY_ID);
                key.setResourceId(resourceId);
                
                return resourceService.updateResource(key, announcement);
            })
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteAnnouncement(
            @PathVariable("id") UUID resourceId,
            @AuthenticationPrincipal Mono<Jwt> jwtMono,
            @RequestHeader("Authorization") String authorizationHeader) {
        
        return jwtMono
            .flatMap(jwt -> profileService.getUserSessionContext(JwtUtil.getUserIdFromToken(jwt), authorizationHeader, null))
            .flatMap(userContext -> {
                 if (userContext.getOrganisation() == null) {
                    return Mono.error(new IllegalStateException("Cannot delete announcement: user has no organisation."));
                 }
                 ResourceKey key = new ResourceKey();
                 key.setOrganizationId(userContext.getOrganisation().getOrganizationId());
                 key.setCategoryId(ANNOUNCEMENT_CATEGORY_ID);
                 key.setResourceId(resourceId);
                 
                 return resourceService.deleteResource(key);
            })
            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}*/


package com.freelance.driver_backend.controller;

import com.freelance.driver_backend.dto.CreateProductRequest;
import com.freelance.driver_backend.model.Product;
import com.freelance.driver_backend.service.ProfileService;
import com.freelance.driver_backend.service.ResourceService;
import com.freelance.driver_backend.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.freelance.driver_backend.repository.ProductRepository;

import java.util.UUID;

@RestController
@RequestMapping("/api/announcements")
@RequiredArgsConstructor
@Slf4j
public class AnnouncementController {

    private final ResourceService resourceService;
    private final ProfileService profileService;
    private final ProductRepository productRepository; 

    // On définit un ID de catégorie fixe pour les annonces
    private static final UUID ANNOUNCEMENT_CATEGORY_ID = UUID.fromString("c1a5b4e0-1234-5678-9abc-def012345678");

    @GetMapping("/my-announcements")
    public Flux<Product> getMyAnnouncements(
            @AuthenticationPrincipal Mono<Jwt> jwtMono,
            @RequestHeader("Authorization") String authorizationHeader) {
        
        // ... (le reste de cette méthode est inchangé)
        return jwtMono
            .flatMap(jwt -> profileService.getUserSessionContext(JwtUtil.getUserIdFromToken(jwt), authorizationHeader, null))
            .flatMapMany(userContext -> {
                if (userContext.getOrganisation() == null) {
                    return Flux.error(new IllegalStateException("User does not have a valid organisation."));
                }
                UUID organizationId = userContext.getOrganisation().getOrganizationId();
                log.info("Fetching announcements (Products) for org {}", organizationId);
                return resourceService.getProductsByCategory(organizationId, ANNOUNCEMENT_CATEGORY_ID, authorizationHeader, null);
            });
    }

    @PostMapping
    public Mono<ResponseEntity<Product>> createAnnouncement(
            @RequestBody CreateProductRequest request,
            @AuthenticationPrincipal Mono<Jwt> jwtMono,
            @RequestHeader("Authorization") String authorizationHeader) {

        // ... (le reste de cette méthode est inchangé)
        return jwtMono
            .flatMap(jwt -> profileService.getUserSessionContext(JwtUtil.getUserIdFromToken(jwt), authorizationHeader, null))
            .flatMap(userContext -> {
                if (userContext.getOrganisation() == null) {
                    return Mono.error(new IllegalStateException("Cannot create announcement: user has no organisation."));
                }
                
                request.setCategoryId(ANNOUNCEMENT_CATEGORY_ID);
                
                log.info("Controller: Creating a new announcement (Product) with name: {}", request.getName());
                return resourceService.createProduct(
                    userContext.getOrganisation().getOrganizationId(), 
                    request, 
                    authorizationHeader, 
                    null
                );
            })
            .map(createdProduct -> new ResponseEntity<>(createdProduct, HttpStatus.CREATED));
    }


    @GetMapping
    public Flux<Product> getPublishedAnnouncements() {
        log.info("Controller: Requête publique pour récupérer les annonces publiées.");
        return productRepository.findByCategoryId(ANNOUNCEMENT_CATEGORY_ID)
                .filter(product -> Boolean.TRUE.equals(product.getIsActive())) // On ne montre que les annonces actives
                .doOnNext(product -> log.info("--> Annonce publique trouvée : {}", product.getName()));
    }



     /**
     * Met à jour une annonce existante. Utilisé pour modifier les détails ou pour publier/dépublier.
     * @param productId L'ID de l'annonce à mettre à jour.
     * @param request Le corps de la requête avec les nouvelles données.
     * @return Le produit mis à jour.
     */
    @PutMapping("/{productId}")
    public Mono<ResponseEntity<Product>> updateAnnouncement(
            @PathVariable UUID productId,
            @RequestBody CreateProductRequest request,
            @AuthenticationPrincipal Mono<Jwt> jwtMono,
            @RequestHeader("Authorization") String authorizationHeader) {
        
        return jwtMono
            .flatMap(jwt -> profileService.getUserSessionContext(JwtUtil.getUserIdFromToken(jwt), authorizationHeader, null))
            .flatMap(userContext -> {
                if (userContext.getOrganisation() == null) {
                    return Mono.error(new IllegalStateException("User does not have a valid organisation."));
                }
                
                log.info("Controller: Updating announcement (Product) ID: {}", productId);
                // On passe l'ID de l'organisation, l'ID du produit et les nouvelles données au service
                return resourceService.updateProduct(
                    userContext.getOrganisation().getOrganizationId(), 
                    productId,
                    request, 
                    authorizationHeader, 
                    null
                );
            })
            .map(ResponseEntity::ok) // Renvoie 200 OK avec le produit mis à jour
            .defaultIfEmpty(ResponseEntity.notFound().build()); // Renvoie 404 si le produit n'est pas trouvé
    }

    /**
     * Supprime une annonce.
     * @param productId L'ID de l'annonce à supprimer.
     * @return Une réponse vide avec le statut 204 No Content.
     */
    @DeleteMapping("/{productId}")
    public Mono<ResponseEntity<Void>> deleteAnnouncement(
            @PathVariable UUID productId,
            @AuthenticationPrincipal Mono<Jwt> jwtMono,
            @RequestHeader("Authorization") String authorizationHeader) {

        return jwtMono
            .flatMap(jwt -> profileService.getUserSessionContext(JwtUtil.getUserIdFromToken(jwt), authorizationHeader, null))
            .flatMap(userContext -> {
                if (userContext.getOrganisation() == null) {
                    return Mono.error(new IllegalStateException("User does not have a valid organisation."));
                }
                
                log.info("Controller: Deleting announcement (Product) ID: {}", productId);
                return resourceService.deleteProduct(
                    userContext.getOrganisation().getOrganizationId(), 
                    productId,
                    authorizationHeader, 
                    null
                );
            })
            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT))); // Renvoie 204 No Content
    }

    // Les méthodes GET public, PUT, et DELETE suivraient la même logique.
}