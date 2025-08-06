package com.freelance.driver_backend.repository;

import com.freelance.driver_backend.model.SmtpSetting;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import java.util.UUID;

public interface SmtpSettingRepository extends ReactiveCassandraRepository<SmtpSetting, UUID> {}