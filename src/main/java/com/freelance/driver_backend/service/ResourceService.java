package com.freelance.driver_backend.service;

import com.freelance.driver_backend.dto.CreateProductRequest;
import com.freelance.driver_backend.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface ResourceService {
    Mono<Product> createProduct(UUID organizationId, CreateProductRequest request, String bearerToken, String publicKey);
    Flux<Product> getProductsByCategory(UUID organizationId, UUID categoryId, String bearerToken, String publicKey);
    Mono<Product> updateProduct(UUID organizationId, UUID productId, CreateProductRequest request, String bearerToken, String publicKey);
    Mono<Void> deleteProduct(UUID organizationId, UUID productId, String bearerToken, String publicKey);
}