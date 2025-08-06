package com.freelance.driver_backend.repository;

import com.freelance.driver_backend.model.Product;
import com.freelance.driver_backend.model.ProductKey;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;
import java.util.UUID;

public interface ProductRepository extends ReactiveCassandraRepository<Product, ProductKey> {

    Flux<Product> findByKeyOrganizationIdAndCategoryId(UUID organizationId, UUID categoryId);

    // VÉRIFIEZ SURTOUT QUE CETTE MÉTHODE EST PRÉSENTE ET SANS FAUTE DE FRAPPE
    @Query("SELECT * FROM products WHERE category_id = ?0 ALLOW FILTERING")
    Flux<Product> findByCategoryId(UUID categoryId);
}