// Fichier : src/main/java/com/freelance/driver_backend/model/mock/MockUser.java
package com.freelance.driver_backend.model.mock;

import lombok.Data;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.UUID;

@Table("mock_users")
@Data
public class MockUser {
    @PrimaryKey
    private UUID id;

    @Indexed // Permet de chercher rapidement par email
    private String email;
    
    private String username;
    private String password; // ATTENTION: En DEV seulement, ne jamais stocker de mdp en clair en prod.
    
    @Column("first_name")
    private String firstName;
    
    @Column("last_name")
    private String lastName;
    
    @Column("phone_number")
    private String phoneNumber;

    // IMPORTANT : On ajoute une colonne pour savoir à quelle organisation cet utilisateur est lié.
    @Column("organisation_id")
    private UUID organisationId;
}