package com.ciandt.summit.bootcamp2022.authentication;

import com.ciandt.summit.bootcamp2022.config.LogConfig;
import com.ciandt.summit.bootcamp2022.config.LogType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final LogConfig log = new LogConfig(CustomAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getPrincipal();
        String name = (String) authentication.getCredentials();

        return getValidationToken(token, name);
    }

    private Authentication getValidationToken(String token, String name) {
        HttpResponse<String> response;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/api/v1/token/authorizer"))
                    .headers("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(
                            String.format("{\"data\":{\"name\":\"%s\",\"token\":\"%s\"}}", name, token)))
                    .build();

            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException | InterruptedException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        boolean isValid = response.statusCode() == 201;

        if (isValid){
            log.create(LogType.INFO, "Successfully validated credentials.");
            return new PreAuthenticatedAuthenticationToken("AuthenticatedUser", name,
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
        }
        else {
             log.create(LogType.ERROR, "Invalid credentials.");
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PreAuthenticatedAuthenticationToken.class.equals(authentication);
    }
}
