package org.example.license.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import java.util.Random;
import org.example.license.config.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/")
@RefreshScope
public class LicenseController {

    @Autowired
    private ServiceConfig config;

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

    public String defaultGetMessage() {
        return "Default message";
    }

    private void randomlyRunLong(){
        Random rand = new Random();
        int randomNum = rand.nextInt((3 - 1) + 1) + 1;
        if (randomNum==3) sleep();
    }
    private void sleep(){
        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
