package com.freelance.driver_backend.repository;

import com.freelance.driver_backend.model.ClientProfile;


import reactor.core.publisher.Mono;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ClientProfileRepository extends ReactiveCassandraRepository<ClientProfile, UUID> {

    // AJOUT DE CETTE MÉTHODE
    // Spring Data comprendra automatiquement qu'il faut chercher sur la colonne "user_id"
    Mono<ClientProfile> findByUserId(UUID userId);
}
