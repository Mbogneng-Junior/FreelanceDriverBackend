package com.freelance.driver_backend.service.external;

import com.freelance.driver_backend.dto.external.ChatUserCreationPayload;
import com.freelance.driver_backend.dto.external.ChatUserLoginPayload;
import com.freelance.driver_backend.dto.external.ChatUserLoginResponse;
import reactor.core.publisher.Mono;

public interface ChatService {
    Mono<ChatUserLoginResponse> registerAndLoginChatUser(ChatUserCreationPayload payload);
    Mono<ChatUserLoginResponse> loginChatUser(ChatUserLoginPayload payload);
}