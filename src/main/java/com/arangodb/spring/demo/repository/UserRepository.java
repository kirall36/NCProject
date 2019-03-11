package com.arangodb.spring.demo.repository;

import com.arangodb.spring.demo.entity.User;
import com.arangodb.springframework.repository.ArangoRepository;

public interface UserRepository extends ArangoRepository<User> {

    User findById(String Id);
    User findByLogin(String Login);
}
