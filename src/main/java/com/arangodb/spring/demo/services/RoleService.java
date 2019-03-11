package com.arangodb.spring.demo.services;

import com.arangodb.spring.demo.entity.Order;
import com.arangodb.spring.demo.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<Role> save(Role role);
    List<Role> findAllRoles();
    Role findById(String Id);
    Boolean isRoleExist(Role role);
    void updateRole(Role role);
    void deleteRoleById(String Id);
    void deleteAllRoles();
}
