package org.example.license.repository;

import org.example.license.model.Check;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckerRedisRepository extends CrudRepository<Check, String> {

}
