package com.arangodb.spring.demo.repository;

import com.arangodb.spring.demo.entity.Role;
import com.arangodb.springframework.repository.ArangoRepository;

public interface RoleRepository extends ArangoRepository<Role> {

    Role findById(String Id);
    Role findByName(String Name);
}
