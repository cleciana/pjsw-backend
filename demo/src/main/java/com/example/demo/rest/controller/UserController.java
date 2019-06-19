package com.example.demo.rest.controller;

import com.example.demo.exception.InvalidFieldException;
import com.example.demo.exception.user.UserNotFoundException;
import com.example.demo.rest.model.User;
import com.example.demo.rest.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController, direciona as requisições para o UserService 
 */
@RestController
@RequestMapping({ "/v1/user" })
public class UserController {

    private UserService userService;

    public UserController(UserService service) {
        this.userService = service;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<User> findById(@PathVariable String id) {
        User user = this.userService.findById(id);

        if(user == null) {
            throw new UserNotFoundException("User not found.");
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/")
    @ResponseBody
    public ResponseEntity<User> create(@RequestBody User user) {

        if (userService.findById(user.getEmail()) != null) {
            throw new InvalidFieldException("User already registered.");
        }
        User newUser = userService.create(user);
        if (newUser == null) {
            throw new InternalError("Something went wrong :/");
        }
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<User> delete(@PathVariable String id) {
        try {
            userService.delete(id);
            return new ResponseEntity<User>(HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalError("Something went wrong :/");
        }
    }

    @PutMapping(value = "/")
    public ResponseEntity<User> update(@RequestBody User user) {
       try {
           User updated = userService.update(user);
           return new ResponseEntity<User>(updated, HttpStatus.OK);
        } catch (Exception e) {
           throw  new InternalError("Something went wrong :/");
        }
    }

}