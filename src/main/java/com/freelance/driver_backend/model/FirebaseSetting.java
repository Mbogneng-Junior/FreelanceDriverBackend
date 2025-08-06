package com.freelance.driver_backend.model;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.*;
import java.util.UUID;

@Table("firebase_settings")
@Data
public class FirebaseSetting {
    @PrimaryKey
    private UUID id;
    @Column("organization_id")
    private UUID organizationId;
    @Column("project_id")
    private String projectId;
    @Column("private_key")
    private String privateKey; // Stocker le JSON de la clé de service
}