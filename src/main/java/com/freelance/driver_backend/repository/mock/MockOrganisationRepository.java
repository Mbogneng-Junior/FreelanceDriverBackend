package com.freelance.driver_backend.repository.mock;

import com.freelance.driver_backend.model.mock.MockOrganisation;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;
import java.util.UUID;

public interface MockOrganisationRepository extends ReactiveCassandraRepository<MockOrganisation, UUID> {
    Flux<MockOrganisation> findByOwnerUserId(UUID ownerUserId);
}