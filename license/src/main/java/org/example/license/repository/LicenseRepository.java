package org.example.license.repository;

import org.example.license.model.License;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseRepository extends CrudRepository<License, Integer> {
}
