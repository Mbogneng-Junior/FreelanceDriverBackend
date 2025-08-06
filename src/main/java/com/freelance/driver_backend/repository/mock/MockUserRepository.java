package com.freelance.driver_backend.repository.mock;

import com.freelance.driver_backend.model.mock.MockUser;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface MockUserRepository extends ReactiveCassandraRepository<MockUser, UUID> {
    Mono<MockUser> findByEmail(String email);
}