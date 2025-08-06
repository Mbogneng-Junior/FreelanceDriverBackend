package com.freelance.driver_backend.service.internal;

import com.freelance.driver_backend.dto.CreateProductRequest;
import com.freelance.driver_backend.model.Product;
import com.freelance.driver_backend.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service
@Profile("dev-resource-mock")
@Slf4j
public class ResourceServiceLocalImpl implements ResourceService {
    private final WebClient localApiClient;

    public ResourceServiceLocalImpl(@Qualifier("localApiWebClient") WebClient localApiClient) {
        this.localApiClient = localApiClient;
    }

    @Override
    public Mono<Product> createProduct(UUID organizationId, CreateProductRequest request, String bearerToken, String publicKey) {
        log.warn("[LOCAL-IMPL] Calling local endpoint to create a product.");
        return localApiClient.post()
                .uri("/api/mock-products/{organizationId}", organizationId)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Product.class);
    }

    @Override
    public Flux<Product> getProductsByCategory(UUID organizationId, UUID categoryId, String bearerToken, String publicKey) {
        log.warn("[LOCAL-IMPL] Calling local endpoint to get products.");
        return localApiClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/mock-products/{organizationId}")
                        .queryParam("categoryId", categoryId)
                        .build(organizationId))
                .retrieve()
                .bodyToFlux(Product.class);
    }

    @Override
    public Mono<Product> updateProduct(UUID organizationId, UUID productId, CreateProductRequest request, String bearerToken, String publicKey) {
        log.warn("[LOCAL-IMPL] Calling local endpoint to update product {}.", productId);
        return localApiClient.put()
                .uri("/api/mock-products/{organizationId}/{productId}", organizationId, productId)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Product.class);
    }

    @Override
    public Mono<Void> deleteProduct(UUID organizationId, UUID productId, String bearerToken, String publicKey) {
        log.warn("[LOCAL-IMPL] Calling local endpoint to delete product {}.", productId);
        return localApiClient.delete()
                .uri("/api/mock-products/{organizationId}/{productId}", organizationId, productId)
                .retrieve()
                .bodyToMono(Void.class);
    }
}