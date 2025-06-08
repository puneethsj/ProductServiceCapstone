package com.example.productservice.utilities;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApplicationUtilities {
    RestTemplate restTemplate;

    public ApplicationUtilities(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void validateToken(String token) {
        if(token == null || token.isEmpty()) {
            throw new RuntimeException("Invalid token: token empty");
        }

        String url = "http://UserServiceCapStone/validate/" + token;
        Boolean isValidToken = restTemplate.getForObject(url, Boolean.class);

        if(Boolean.FALSE.equals(isValidToken)) {
            throw new RuntimeException("Invalid token: token is invalid");
        }

    }
}
