package com.freelance.driver_backend.model;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import java.util.UUID;
import org.springframework.data.cassandra.core.mapping.Indexed; 

@Table("client_profiles")
@Data
public class ClientProfile {
    @PrimaryKey
    private UUID id;

    @Indexed 
    @Column("user_id")
    private UUID userId;
    @Column("organisation_id")
    private UUID organisationId;
    @Column("company_name")
    private String companyName;
    @Column("contact_email")
    private String contactEmail;
    @Column("phone_number")
    private String phoneNumber;
}