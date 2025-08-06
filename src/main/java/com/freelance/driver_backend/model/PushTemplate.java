package com.freelance.driver_backend.model;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.*;
import java.util.UUID;

@Table("push_templates")
@Data
public class PushTemplate {
    @PrimaryKey
    private UUID id;
    @Column("organization_id")
    private UUID organizationId;
    @Column("setting_id")
    private UUID settingId; // Lien vers FirebaseSetting
    private String title;   // Ex: "Nouvelle course disponible !"
    private String body;    // Ex: "Acceptez la course de {{clientName}} pour {{destination}}."
}