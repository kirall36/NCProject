package com.arangodb.spring.demo.controllers;

import com.arangodb.spring.demo.entity.User;
import com.arangodb.spring.demo.services.UserService;
import com.arangodb.spring.demo.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

    // -------------------Retrieve All Users---------------------------------------------
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    // -------------------Retrieve Single User------------------------------------------
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") String id) {
        User user = userService.findById("user/" + id);
        if ( user == null) {
            return new ResponseEntity(new CustomErrorType("User with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // -------------------Create a User-------------------------------------------
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody @Valid User user, BindingResult bindingResult) {

        if(bindingResult.hasErrors())
        {
            return new ResponseEntity(new CustomErrorType(bindingResult.getFieldErrors().toString()), HttpStatus.CONFLICT);
        }
        if (userService.isUserExist(user)) {
            return new ResponseEntity(new CustomErrorType("Unable to create. A User with login " +
                    user.getLogin() + " already exist."),HttpStatus.CONFLICT);
        }

        user.setPassword(encoder().encode(user.getPassword()));
        userService.save(user);

        //HttpHeaders headers = new HttpHeaders();
        //headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId().substring(5)).toUri());
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    // ------------------- Update a User ------------------------------------------------

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") String id, @RequestBody @Valid User user, BindingResult bindingResult) {

        if(bindingResult.hasErrors())
        {
            return new ResponseEntity(new CustomErrorType(bindingResult.getFieldErrors().toString()), HttpStatus.CONFLICT);
        }
        User currentUser = userService.findById("user/" + id);

        if (currentUser == null) {
            return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentUser.setLogin(user.getLogin());
        currentUser.setPassword(encoder().encode(user.getPassword()));
        currentUser.setRoles(user.getRoles());

        userService.updateUser(currentUser);

        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }

    // ------------------- Delete a User-----------------------------------------

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {

        User user = userService.findById("user/" + id);
        if (user == null) {
            return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        userService.deleteUserById(id);

        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Users-----------------------------

    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers() {

        userService.deleteAllUsers();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}