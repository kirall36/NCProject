package com.arangodb.spring.demo.services;

import com.arangodb.spring.demo.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User findById(String Id);
    User findByLogin(String login);
    Boolean isUserExist(User user);
    User save(User user);
    void updateUser(User user);
    void deleteUserById(String Id);
    void deleteAllUsers();
}
