package com.example.demo.rest.controller;

import java.util.Date;

import com.example.demo.exception.user.InvalidPasswordException;
import com.example.demo.exception.user.UserNotFoundException;
import com.example.demo.rest.model.User;
import com.example.demo.rest.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * LoginController
 */
@RestController()
@RequestMapping({"v1/auth"})
public class LoginController {

    private final String TOKEN_KEY = "applepen";

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginResponse authenticate(@RequestBody User user) {
        User authUser = userService.findById(user.getEmail());

        if(authUser == null) {
            throw new UserNotFoundException("User not found.");
        }
        if (!authUser.getPassword().equals(user.getPassword())) {
            throw new  InvalidPasswordException("Wrong password.");
        }

        String token = Jwts.builder()
        .setSubject(authUser.getEmail())
        .signWith(SignatureAlgorithm.ES512, TOKEN_KEY)
        .setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
        .compact();

        return new LoginResponse(token);
    }

    private class LoginResponse {

        public String token;

        public LoginResponse(String token) {
            this.token = token;
        }
    
        
    }
}

