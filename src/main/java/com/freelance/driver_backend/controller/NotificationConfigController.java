package com.freelance.driver_backend.controller;

import com.freelance.driver_backend.dto.external.NotificationRequest;
import com.freelance.driver_backend.model.DesignTemplate;
import com.freelance.driver_backend.model.EmailTemplate;
import com.freelance.driver_backend.model.SmtpSetting;
import com.freelance.driver_backend.repository.DesignTemplateRepository;
import com.freelance.driver_backend.repository.EmailTemplateRepository;
import com.freelance.driver_backend.repository.SmtpSettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.freelance.driver_backend.model.FirebaseSetting;
import com.freelance.driver_backend.model.PushTemplate;
import com.freelance.driver_backend.repository.FirebaseSettingRepository;
import com.freelance.driver_backend.repository.PushTemplateRepository;

import com.freelance.driver_backend.service.external.NotificationService; 
import org.springframework.http.ResponseEntity;

import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/mock-notifications/{organizationId}")
@RequiredArgsConstructor
public class NotificationConfigController {

    private final SmtpSettingRepository smtpSettingRepository;
    private final DesignTemplateRepository designTemplateRepository;
    private final EmailTemplateRepository emailTemplateRepository;
    private final NotificationService notificationService;
    private final FirebaseSettingRepository firebaseSettingRepository;
    private final PushTemplateRepository pushTemplateRepository;

    @PostMapping("/smtp-settings")
    public Mono<SmtpSetting> createSmtpSetting(@PathVariable UUID organizationId, @RequestBody SmtpSetting setting) {
        setting.setId(UUID.randomUUID());
        setting.setOrganizationId(organizationId);
        return smtpSettingRepository.save(setting);
    }

    @PostMapping("/design-templates")
    public Mono<DesignTemplate> createDesignTemplate(@PathVariable UUID organizationId, @RequestBody DesignTemplate template) {
        template.setId(UUID.randomUUID());
        template.setOrganizationId(organizationId);
        return designTemplateRepository.save(template);
    }

    @PostMapping("/email-templates")
    public Mono<EmailTemplate> createEmailTemplate(@PathVariable UUID organizationId, @RequestBody EmailTemplate template) {
        template.setId(UUID.randomUUID());
        template.setOrganizationId(organizationId);
        return emailTemplateRepository.save(template);
    }

     @PostMapping("/send-test-email")
    public Mono<ResponseEntity<String>> sendTestEmail(
            @PathVariable UUID organizationId,
            @RequestBody NotificationRequest request) {
        
        // Nous n'avons pas besoin du token ou de la clé publique ici car c'est un test local
        // qui appelle notre service "mock".
        return notificationService.sendEmailNotification(organizationId, request, "mock-token", "mock-key")
            .map(success -> {
                if (Boolean.TRUE.equals(success)) {
                    return ResponseEntity.ok("Test email sent successfully!");
                } else {
                    return ResponseEntity.status(500).body("Failed to send test email.");
                }
            })
            .onErrorResume(e -> Mono.just(ResponseEntity.status(500).body("Error: " + e.getMessage())));
    }


    @PostMapping("/firebase-settings")
    public Mono<FirebaseSetting> createFirebaseSetting(@PathVariable UUID organizationId, @RequestBody FirebaseSetting setting) {
        setting.setId(UUID.randomUUID());
        setting.setOrganizationId(organizationId);
        return firebaseSettingRepository.save(setting);
    }

    @PostMapping("/push-templates")
    public Mono<PushTemplate> createPushTemplate(@PathVariable UUID organizationId, @RequestBody PushTemplate template) {
        template.setId(UUID.randomUUID());
        template.setOrganizationId(organizationId);
        return pushTemplateRepository.save(template);
    }
    
}
