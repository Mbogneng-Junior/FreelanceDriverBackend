package com.freelance.driver_backend.repository;

import com.freelance.driver_backend.model.Resource;
import com.freelance.driver_backend.model.ResourceKey; // <-- Import de la nouvelle clé
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import java.util.UUID;

@Repository
// Le deuxième paramètre générique est maintenant ResourceKey
public interface ResourceRepository extends ReactiveCassandraRepository<Resource, ResourceKey> { 
    
    // Cette méthode est maintenant beaucoup plus efficace !
    Flux<Resource> findByKeyOrganizationIdAndKeyCategoryId(UUID organizationId, String categoryId);
    
    // On doit ajouter ALLOW FILTERING car category_id n'est pas la clé de partition
    @Query("SELECT * FROM resources WHERE category_id = ?0 ALLOW FILTERING")
    Flux<Resource> findByKeyCategoryId(String categoryId);
}