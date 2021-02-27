package org.example.checker.controller;

import javax.annotation.security.RolesAllowed;
import org.example.checker.service.CheckerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/")
@RefreshScope
public class CheckerController {

    @Autowired
    private CheckerService checkerService;

    @RolesAllowed({"ADMIN"})
    @GetMapping("/check/license/{license}")
    public String checkLicense(@PathVariable String license) {
        boolean isExpired = checkerService.checkLicense(license);
        return String.valueOf(isExpired);
    }
}
