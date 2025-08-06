package com.freelance.driver_backend.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;


import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {

    @Value("${microservices.chat-service.url}")
    private String chatServiceUrl;

    @Value("${microservices.auth-service.url}")
    private String authServiceUrl;

    @Value("${microservices.organisation-service.url}")
    private String organisationServiceUrl;

    @Value("${server.port}")
    private String localServerPort;
    
    @Value("${microservices.resource-service.url}")
    private String resourceServiceUrl;

    /**
     * Crée un HttpClient réutilisable avec des timeouts et une configuration de connexion robustes.
     * @return HttpClient configuré
     */
    private HttpClient createConfiguredHttpClient() {
        return HttpClient.create()
                // Timeout pour établir la connexion initiale (incluant le handshake SSL)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 20000) // 20 secondes
                // Timeout global pour recevoir une réponse après l'envoi de la requête
                .responseTimeout(Duration.ofSeconds(20))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(20, TimeUnit.SECONDS))
                            .addHandlerLast(new WriteTimeoutHandler(20, TimeUnit.SECONDS)));
    }
    
    @Bean
    @Qualifier("authServiceWebClient")
    public WebClient authServiceWebClient() {
        return WebClient.builder()
                .baseUrl(authServiceUrl)
                .clientConnector(new ReactorClientHttpConnector(createConfiguredHttpClient()))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    // --- MODIFICATION IMPORTANTE ICI ---
    @Bean
    @Qualifier("organisationServiceWebClient")
    public WebClient organisationServiceWebClient() {
        // On utilise la même configuration robuste pour le service d'organisation
        return WebClient.builder()
                .baseUrl(organisationServiceUrl)
                .clientConnector(new ReactorClientHttpConnector(createConfiguredHttpClient())) // <-- On applique la config
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    
    @Bean
    @Qualifier("chatServiceWebClient")
    public WebClient chatServiceWebClient() {
        return WebClient.builder()
                .baseUrl(chatServiceUrl)
                .clientConnector(new ReactorClientHttpConnector(createConfiguredHttpClient()))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }


    @Bean
    @Qualifier("localApiWebClient")
    public WebClient localApiWebClient() {
        return WebClient.builder().baseUrl("http://localhost:" + localServerPort).build();
    }
    
    @Bean
    @Qualifier("externalResourceWebClient")
    public WebClient externalResourceWebClient() {
        return WebClient.builder().baseUrl(resourceServiceUrl).build();
    }
}