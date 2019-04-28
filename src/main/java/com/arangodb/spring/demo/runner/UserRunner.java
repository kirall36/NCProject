package com.arangodb.spring.demo.runner;

import com.arangodb.spring.demo.entity.Role;
import com.arangodb.spring.demo.entity.User;
import com.arangodb.spring.demo.repository.RoleRepository;
import com.arangodb.spring.demo.repository.UserRepository;
import com.arangodb.spring.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Collection;


@ComponentScan("com.arangodb.spring.demo")
public class UserRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... strings) throws Exception {
            User user = userRepository.findByLogin("admin");
            System.out.println("user " + userService.isUserExist(user));
            //User user1 = new User("testUser", "2314");
            //User user2 = new User("dsl;fj", "1242");
            //System.out.println("user1 " + userService.isUserExist(user1));
            //System.out.println("user2 " + userService.isUserExist(user2));

        //        Role r1 = new Role("User");
//        Role r2 = new Role("Administrator");
//        roleRepository.save(r1);
//        roleRepository.save(r2);
//        System.out.println("##All roles");
//        Iterable<Role> allRoles = roleRepository.findAll();
//        allRoles.forEach(System.out::println);
//
//
//        User u = new User("admin", "0000", createRoles());
//        userRepository.save(u);
//        u = new User("kir", "1111", createOneRole());
//        userRepository.save(u);
//        System.out.println("##All users after saving");
//        Iterable<User> allUsers = userRepository.findAll();
//        allUsers.forEach(System.out::println);
    }

    public Collection<Role> createRoles(){
        return Arrays.asList(roleRepository.findByName("User"), roleRepository.findByName("Administrator"));
    }

    public Collection<Role> createOneRole(){
        return Arrays.asList(roleRepository.findByName("User"));
    }
}
