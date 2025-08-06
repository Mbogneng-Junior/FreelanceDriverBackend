package com.freelance.driver_backend.config;

import com.datastax.oss.driver.api.core.config.DefaultDriverOption;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.config.SessionBuilderConfigurer;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;

import java.time.Duration;

@Configuration
@EnableReactiveCassandraRepositories(basePackages = "com.freelance.driver_backend.repository")
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Override
    protected String getKeyspaceName() {
        return "freelancebd";
    }

    @Override
    protected String getContactPoints() {
        return "127.0.0.1";
    }

    @Override
    protected int getPort() {
        return 9042;
    }

    @Override
    protected String getLocalDataCenter() {
        return "datacenter1";
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    /**
     * Configuration avancée pour surcharger les timeouts du driver Cassandra.
     * Cette méthode est plus robuste que la configuration via application.properties.
     */
    @Override
    protected SessionBuilderConfigurer getSessionBuilderConfigurer() {
        return sessionBuilder -> {
            DriverConfigLoader configLoader = DriverConfigLoader.programmaticBuilder()
                    // Augmente le timeout pour toutes les requêtes à 20 secondes.
                    .withDuration(DefaultDriverOption.REQUEST_TIMEOUT, Duration.ofSeconds(20))
                    // Augmente le timeout pour la connexion initiale.
                    .withDuration(DefaultDriverOption.CONNECTION_CONNECT_TIMEOUT, Duration.ofSeconds(20))
                    // Augmente le timeout pour l'initialisation du schéma (création des tables/index).
                    .withDuration(DefaultDriverOption.CONTROL_CONNECTION_TIMEOUT, Duration.ofSeconds(20))
                    .build();
            
            return sessionBuilder.withConfigLoader(configLoader);
        };
    }
}