package org.example.license;

import lombok.extern.slf4j.Slf4j;
import org.example.license.model.CheckerChangeModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableCircuitBreaker
@EnableEurekaClient
@EnableBinding(Sink.class)
@Slf4j
public class LicenseApp {

    public static void main(String[] args) {
        SpringApplication.run(LicenseApp.class, args);
    }

    @StreamListener(Sink.INPUT)
    public void loggerSink(CheckerChangeModel model) {
        log.info("Received an {} event from checker service for license {}", model.getAction(), model.getLicense());
    }
}
