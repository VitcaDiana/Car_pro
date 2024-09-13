//package com.project.CarPro.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//
//@Service
//public class EnodeApiService {
//    private final WebClient webClient;
//    public final EnodeApiProperties enodeApiProperties;
//
//    @Autowired
//    public EnodeApiService(WebClient.Builder webClientBuilder, EnodeApiProperties enodeApiProperties) {
//        this.enodeApiProperties = enodeApiProperties;
//        this.webClient = webClientBuilder
//                .baseUrl(enodeApiProperties.getBaseUrl())  // Use the base URL from properties
//                .build();
//    }
//
//    public String getUserData(String userId) {
//        return this.webClient
//                .get()
//                .uri("/users/{userId}", userId)
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//    }
//}
