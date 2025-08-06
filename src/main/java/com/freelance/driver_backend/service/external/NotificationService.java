package com.freelance.driver_backend.service.external;

import com.freelance.driver_backend.dto.external.NotificationRequest;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface NotificationService {

    /**
     * Envoie une notification par email en utilisant le service de notification.
     *
     * @param organisationId L'ID de l'organisation qui envoie la notification.
     * @param request        L'objet contenant les détails de la notification (template, destinataires, etc.).
     * @param userBearerToken  Le token de l'utilisateur authentifié.
     * @param publicKey      La clé publique de l'API.
     * @return Un Mono<Boolean> qui retourne true si la notification a été acceptée pour envoi.
     */
    Mono<Boolean> sendEmailNotification(UUID organisationId, NotificationRequest request, String userBearerToken, String publicKey);
    Mono<Boolean> sendPushNotification(UUID organisationId, NotificationRequest request, String userBearerToken, String publicKey);

}