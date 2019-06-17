package com.example.demo.rest.controller;

import com.example.demo.rest.service.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 */
@RestController
@RequestMapping({ "/v1/user" })
public class UserController {

    private UserService userService;

    public UserController(UserService service) {

    }

}