package com.freelance.driver_backend.model;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import java.util.UUID;

@Table("design_templates")
@Data
public class DesignTemplate {
    @PrimaryKey
    private UUID id;
    @Column("organization_id")
    private UUID organizationId;
    private String title;
    private String html;
    private String subject;
}