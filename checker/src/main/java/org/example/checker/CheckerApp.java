package org.example.checker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

@SpringBootApplication
@RefreshScope
//@EnableCircuitBreaker
//@EnableEurekaClient
@EnableBinding(Source.class)
public class CheckerApp {
    public static void main(String[] args) {
        SpringApplication.run(CheckerApp.class, args);
    }
}
