package com.arangodb.spring.demo.services;

import com.arangodb.spring.demo.entity.Role;
import com.arangodb.spring.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> save(Role role) {
        return Optional.of(roleRepository.save(role));
    }

    @Override
    public List<Role> findAllRoles() {
        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach(roles::add);
        return roles;
    }

    @Override
    public Role findById(String Id) {
        return roleRepository.findById(Id);
    }

    @Override
    public Boolean isRoleExist(Role role) {
        return roleRepository.exists(role.getId());
    }

    @Override
    public void updateRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void deleteRoleById(String Id) {
        roleRepository.delete(Id);
    }

    @Override
    public void deleteAllRoles() {
        roleRepository.deleteAll();
    }
}
