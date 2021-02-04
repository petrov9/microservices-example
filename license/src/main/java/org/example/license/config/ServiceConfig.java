package org.example.license.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceConfig {

    @Value("${my.message}")
    private String message;

    public String getMessage() {
        return message;
    }
}
