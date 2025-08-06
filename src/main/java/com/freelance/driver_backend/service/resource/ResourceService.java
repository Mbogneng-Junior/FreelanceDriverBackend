package com.freelance.driver_backend.service.resource;

import com.freelance.driver_backend.model.Resource;
import com.freelance.driver_backend.model.ResourceKey; // <-- Nouvel import
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface ResourceService {

    // Ces méthodes ne changent pas car elles filtrent sur des listes
    Flux<Resource> getResourcesByOrganisationAndCategory(UUID organisationId, String categoryId);
    Flux<Resource> getResourcesByCategory(String categoryId);

    // Cette méthode ne change pas car elle ne concerne que la création
    Mono<Resource> createResource(Resource resource);

    // --- MODIFICATIONS ICI ---
    // Pour trouver, mettre à jour ou supprimer une ressource spécifique, on a besoin de sa clé complète
    Mono<Resource> getResourceById(ResourceKey key);
    Mono<Resource> updateResource(ResourceKey key, Resource resource);
    Mono<Void> deleteResource(ResourceKey key);
}