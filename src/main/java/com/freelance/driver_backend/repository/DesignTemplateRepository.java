package com.freelance.driver_backend.repository;

import com.freelance.driver_backend.model.DesignTemplate;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import java.util.UUID;

public interface DesignTemplateRepository extends ReactiveCassandraRepository<DesignTemplate, UUID> {}