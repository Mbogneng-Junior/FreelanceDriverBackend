package com.freelance.driver_backend.repository;

import com.freelance.driver_backend.model.PushTemplate;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import java.util.UUID;

public interface PushTemplateRepository extends ReactiveCassandraRepository<PushTemplate, UUID> {}