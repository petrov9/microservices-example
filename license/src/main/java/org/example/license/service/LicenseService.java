package org.example.license.service;

import org.example.license.config.ServiceConfig;
import org.example.license.model.License;
import org.example.license.repository.LicenseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LicenseService {

    @Autowired
    private LicenseRepository licenseRepository;

    @Autowired
    private ServiceConfig config;

    public List<License> findAll() {
        return (List<License>) licenseRepository.findAll();
    }

    public License create() {
        License license = new License();
        license.setMessage(config.getMessage());
        return licenseRepository.save(license);
    }
}
