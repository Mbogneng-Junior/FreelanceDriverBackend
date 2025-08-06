package com.freelance.driver_backend.model;

import com.fasterxml.jackson.annotation.JsonProperty; // <-- AJOUTER CET IMPORT
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import java.util.UUID;

@Table("smtp_settings")
@Data
public class SmtpSetting {
    @PrimaryKey
    private UUID id;
    
    @Column("organization_id")
    @JsonProperty("organization_id") // Pour la sortie JSON
    private UUID organizationId;
    
    private String host;
    private int port;
    private String encryption;
    private String username;
    private String password;
    
    @Column("sender_email")
    @JsonProperty("sender_email") // <-- CORRECTION ICI
    private String senderEmail;
    
    @Column("sender_name")
    @JsonProperty("sender_name") // <-- CORRECTION ICI
    private String senderName;
}