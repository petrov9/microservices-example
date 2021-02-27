package org.example.license.controller;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.security.RolesAllowed;
import org.example.license.client.CheckerRestTemplateClient;
import org.example.license.config.ServiceConfig;
import org.example.license.model.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/")
@RefreshScope
public class LicenseController {

    private Map<Integer, License> storage = new ConcurrentHashMap<>();

    @Autowired
    private ServiceConfig config;

    @Autowired
    private CheckerRestTemplateClient checkerClient;

    @GetMapping
    /*@HystrixCommand(fallbackMethod = "defaultGetMessage",
        threadPoolKey = "myThreadPool",
        threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "30"),
            @HystrixProperty(name = "mazQueueSize", value = "10")
        })*/
    public String getMessage() {
//        randomlyRunLong();
        return config.getMessage();
    }

    @RolesAllowed({"ADMIN", "USER"})
    @GetMapping(value = "v1/hello/{name}")
    public String helloMessagev1(@PathVariable String name) {
        return "Hello, " + name;
    }

    @RolesAllowed({"ADMIN", "USER"})
    @GetMapping(value = "v2/hello/{name}")
    public String helloMessagev2(@PathVariable String name) {
        return "Como estas, " + name;
    }

    @RolesAllowed({"ADMIN"})
    @PostMapping
    public String addLicense(@RequestBody License license) {
        storage.put(license.getId(), license);
        return "Successfully put license: " + license;
    }

    @GetMapping(value = "/check/{license}")
    public String checkLicense(@PathVariable String license) {
        Boolean isExpired = checkerClient.checkLicense(license);

        return isExpired ? String.format("Лицензия %s не просрочена", license) :
            String.format("Лицензия %s просрочена, срочно обновите ее", license);
    }

    public String defaultGetMessage() {
        return "Default message";
    }

    private void randomlyRunLong() {
        Random rand = new Random();
        int randomNum = rand.nextInt((3 - 1) + 1) + 1;
        if (randomNum == 3) {
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
