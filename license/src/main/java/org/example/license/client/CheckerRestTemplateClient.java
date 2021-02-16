package org.example.license.client;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CheckerRestTemplateClient {

    @Autowired
    private KeycloakRestTemplate keycloakRestTemplate;

    public String checkLicense(String license) {
        ResponseEntity<String> response = keycloakRestTemplate.exchange("http://localhost:7856/checker/check/license/{license}", HttpMethod.GET,
            null, String.class, license);

        return response.getBody();
    }

}
