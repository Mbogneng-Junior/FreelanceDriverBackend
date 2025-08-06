package com.freelance.driver_backend.service.external.mock;

import com.freelance.driver_backend.dto.external.*;
import com.freelance.driver_backend.service.external.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Profile("dev-mock")
@Slf4j
public class MockChatServiceImpl implements ChatService {

    @Override
    public Mono<ChatUserLoginResponse> registerAndLoginChatUser(ChatUserCreationPayload payload) {
        log.warn("[MOCK-CHAT] Simulating registration and login for chat user: {}", payload.getLogin());
        return loginChatUser(new ChatUserLoginPayload(null, payload.getLogin(), payload.getSecret()));
    }

    @Override
    public Mono<ChatUserLoginResponse> loginChatUser(ChatUserLoginPayload payload) {
        log.warn("[MOCK-CHAT] Simulating login for chat user: {}", payload.getLogin());
        
        ChatUserCreationResponse fakeUser = new ChatUserCreationResponse();
        fakeUser.setId(UUID.randomUUID().toString());
        fakeUser.setExternalId(payload.getLogin()); // Utilise le login comme externalId pour le mock
        fakeUser.setDisplayName("Mock " + payload.getLogin());
        
        ChatUserLoginResponse response = new ChatUserLoginResponse();
        response.setData(fakeUser);
        response.setToken("mock-chat-token-" + UUID.randomUUID());
        
        return Mono.just(response);
    }
}