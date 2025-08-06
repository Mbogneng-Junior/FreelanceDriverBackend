package com.freelance.driver_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;
import org.springframework.context.annotation.Primary;

@Configuration
public class ThymeleafConfig {

    /**
     * Configure un moteur de template Thymeleaf capable de traiter des chaînes de caractères.
     * On le marque comme @Primary pour qu'il soit choisi par défaut lors de l'injection
     * de dépendances, au lieu de celui configuré automatiquement par Spring Boot.
     * @return Une instance de SpringTemplateEngine.
     */
    @Bean
    @Primary // <-- AJOUTER CETTE ANNOTATION
    public SpringTemplateEngine springTemplateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        
        // Créer un "resolver" qui sait lire des chaînes de caractères
        StringTemplateResolver stringTemplateResolver = new StringTemplateResolver();
        stringTemplateResolver.setTemplateMode(TemplateMode.HTML); // Préciser qu'on traite du HTML
        
        // Ajouter ce resolver au moteur de template
        templateEngine.addTemplateResolver(stringTemplateResolver);
        
        return templateEngine;
    }
}