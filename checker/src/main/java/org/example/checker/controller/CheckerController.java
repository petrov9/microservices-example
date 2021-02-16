package org.example.checker.controller;

import javax.annotation.security.RolesAllowed;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/")
@RefreshScope
public class CheckerController {

    @RolesAllowed({"ADMIN"})
    @GetMapping("/check/license/{license}")
    public String checkLicense(@PathVariable String license) {
        boolean isGoodLicense = Math.random() > 0.5;

        return isGoodLicense ? String.format("Лицензия %s не просрочена", license) :
            String.format("Лицензия %s просрочена, срочно обновите ее", license);
    }
}
