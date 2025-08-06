package com.freelance.driver_backend.repository;

import com.freelance.driver_backend.model.EmailTemplate;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import java.util.UUID;

public interface EmailTemplateRepository extends ReactiveCassandraRepository<EmailTemplate, UUID> {}