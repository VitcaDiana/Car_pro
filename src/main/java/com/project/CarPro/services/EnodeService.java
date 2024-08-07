package com.project.CarPro.services;

import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EnodeService {

    private final OAuth2AuthorizedClientManager authorizedClientManager;
    private final WebClient webClient;

    public EnodeService(OAuth2AuthorizedClientManager authorizedClientManager, WebClient.Builder webClientBuilder) {
        this.authorizedClientManager = authorizedClientManager;
        this.webClient = webClientBuilder.build();
    }

    public String createLinkSession(String userId) {
        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId("enode")
                .principal("enode-client")
                .build();

        OAuth2AuthorizedClient authorizedClient = this.authorizedClientManager.authorize(authorizeRequest);
        if (authorizedClient == null) {
            throw new IllegalStateException("Failed to authorize client");
        }

        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        String url = "https://enode-api.sandbox.enode.io/users/" + userId + "/link";

        String redirectUri = "localhost:8080/link/callback";

        return this.webClient.post()
                .uri(url)
                .header("Authorization", "Bearer " + accessToken.getTokenValue())
                .header("Content-Type", "application/json")
                .bodyValue("{ \"vendorType\": \"vehicle\", \"scopes\": [\"vehicle:read:data\", \"vehicle:read:location\", \"vehicle:control:charging\"], \"language\": \"en-US\", \"redirectUri\": \"" + redirectUri + "\" }")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
