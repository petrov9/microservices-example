package org.example.license.client;

import lombok.extern.slf4j.Slf4j;
import org.example.license.model.Check;
import org.example.license.repository.CheckerRedisRepository;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class CheckerRestTemplateClient {

    @Autowired
    private KeycloakRestTemplate keycloakRestTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CheckerRedisRepository repository;

    public Boolean checkLicense(String license) {
        Check check = checkRedisCache(license);
        if (check != null) {
            log.debug("Found object {} in redis cache, using it", check);
            return check.getIsExpired();
        }

        ResponseEntity<String> response = keycloakRestTemplate.exchange("http://localhost:7856/checker/check/license/{license}", HttpMethod.GET,
            null, String.class, license);

        Boolean isExpired = Boolean.valueOf(response.getBody());

        if (isExpired != null) {
            check = new Check();
            check.setId(license);
            check.setIsExpired(isExpired);
            cacheCheckObject(check);
            log.debug("Put object {} in redis cache", check);
        }

        return isExpired;
    }

    private Check checkRedisCache(String license) {
        try {
            return repository.findById(license).orElse(null);
        } catch (Exception e) {
            log.error("Error encountered while trying to retrieve license {} check Redis Cache.  Exception {}", license, e);
            return null;
        }
    }

    private void cacheCheckObject(Check check) {
        try {
            repository.save(check);
        } catch (Exception e) {
            log.error("Unable to cache check entity {} in Redis. Exception {}", check.getId(), e);
        }
    }

}
