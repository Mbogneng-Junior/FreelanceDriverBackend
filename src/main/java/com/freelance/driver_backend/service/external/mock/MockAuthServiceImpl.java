package com.freelance.driver_backend.service.external.mock;

import com.freelance.driver_backend.dto.external.*;
import com.freelance.driver_backend.model.mock.MockUser;
import com.freelance.driver_backend.repository.mock.MockUserRepository;
import com.freelance.driver_backend.service.external.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@Profile("dev-mock")
@RequiredArgsConstructor
@Slf4j
public class MockAuthServiceImpl implements AuthService {

    private final MockUserRepository mockUserRepository;

    @Override
    public Mono<OAuthTokenResponse> getClientCredentialsToken(String clientId, String clientSecret) {
        log.warn("[MOCK] Generating M2M token.");
        OAuthTokenResponse token = new OAuthTokenResponse();
        token.setAccessToken("mock-m2m-token-" + UUID.randomUUID());
        token.setTokenType("Bearer");
        token.setExpiresIn(3600);
        return Mono.just(token);
    }

    @Override
    public Mono<UserDto> registerUser(RegistrationRequest request, String m2mBearerToken) {
        log.warn("[MOCK] Attempting to register user '{}' in local DB.", request.getEmail());
        
        // On cherche l'utilisateur existant.
        return mockUserRepository.findByEmail(request.getEmail())
            // `hasElement()` transforme le Mono<MockUser> en Mono<Boolean>.
            .hasElement() 
            .flatMap(userExists -> {
                // Si l'utilisateur existe, on lève une erreur.
                if (userExists) {
                    log.error("[MOCK] User with email {} already exists.", request.getEmail());
                    return Mono.error(new RuntimeException("User with this email already exists"));
                }
                
                // Sinon, on procède à la création.
                log.warn("[MOCK] User not found, proceeding with creation.");
                MockUser newUser = new MockUser();
                newUser.setId(UUID.randomUUID());
                newUser.setEmail(request.getEmail());
                newUser.setUsername(request.getUsername());
                newUser.setPassword(request.getPassword()); // En DEV seulement !
                newUser.setFirstName(request.getFirstName());
                newUser.setLastName(request.getLastName());
                newUser.setPhoneNumber(request.getPhoneNumber());

                // On sauvegarde le nouvel utilisateur et on le transforme en DTO.
                // Le type de retour est bien Mono<UserDto>.
                return mockUserRepository.save(newUser).map(this::mapToUserDto);
            });
    }

    @Override
    public Mono<LoginResponse> loginUser(LoginRequest request, String m2mBearerToken) {
        log.warn("[MOCK] Logging in user '{}' from local DB.", request.getUsername());
        
        return mockUserRepository.findByEmail(request.getUsername())
                .filter(user -> user.getPassword().equals(request.getPassword()))
                .map(user -> {
                    LoginResponse response = new LoginResponse();
                    
                    LoginResponse.AccessToken token = new LoginResponse.AccessToken();
                    token.setToken("mock-user-token-" + user.getId());
                    token.setExpiresIn(3600);
                    token.setType("Bearer");
                    response.setAccessToken(token);

                    LoginResponse.UserInfo userInfo = new LoginResponse.UserInfo();
                    userInfo.setId(user.getId());
                    userInfo.setEmail(user.getEmail());
                    userInfo.setFirstName(user.getFirstName());
                    userInfo.setLastName(user.getLastName());
                    userInfo.setPhoneNumber(user.getPhoneNumber());
                    userInfo.setUsername(user.getUsername());
                    userInfo.setEmailVerified(true);
                    userInfo.setPhoneNumberVerified(true);
                    response.setUser(userInfo);
                    
                    return response;
                })
                .switchIfEmpty(Mono.error(new RuntimeException("Mock Login Failed: User not found or password incorrect.")));
    }

    private UserDto mapToUserDto(MockUser user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setEnabled(true);
        dto.setEmailVerified(true);
        dto.setPhoneNumberVerified(true);
        dto.setCreatedAt(OffsetDateTime.now());
        dto.setUpdatedAt(OffsetDateTime.now());
        return dto;
    }
}