package com.freelance.driver_backend.repository;

import com.freelance.driver_backend.model.FirebaseSetting;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import java.util.UUID;

public interface FirebaseSettingRepository extends ReactiveCassandraRepository<FirebaseSetting, UUID> {}