package com.freelance.driver_backend.model.mock; // <-- Vérifiez ce package

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.*;
import java.util.UUID;

@Table("mock_organisations") // <-- Vérifiez le nom de la table
@Data
public class MockOrganisation {
    @PrimaryKey
    @Column("organization_id")
    private UUID organizationId;

    @Column("long_name")
    private String longName;

    private String description;

    @Indexed
    @Column("owner_user_id")
    private UUID ownerUserId;
}