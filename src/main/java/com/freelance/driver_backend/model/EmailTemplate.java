package com.freelance.driver_backend.model;

import com.fasterxml.jackson.annotation.JsonProperty; // <-- AJOUTER CET IMPORT
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import java.util.UUID;

@Table("email_templates")
@Data
public class EmailTemplate {
    @PrimaryKey
    private UUID id;
    
    @Column("organization_id")
    @JsonProperty("organization_id")
    private UUID organizationId;
    
    @Column("setting_id")
    @JsonProperty("setting_id") // <-- CORRECTION ICI
    private UUID settingId;
    
    @Column("design_template_id")
    @JsonProperty("design_template_id") // <-- CORRECTION ICI
    private UUID designTemplateId;
    
    private String title;
}