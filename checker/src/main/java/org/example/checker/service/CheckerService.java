package org.example.checker.service;

import lombok.extern.slf4j.Slf4j;
import org.example.checker.events.source.SimpleSourceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CheckerService {

    @Autowired
    private SimpleSourceBean sourceBean;

    public boolean checkLicense(String license) {
        boolean isExpired = Math.random() > 0.5;
        sourceBean.publishCheckerChange("check", license);

        return isExpired;
    }
}
