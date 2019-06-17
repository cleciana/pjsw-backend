package com.example.demo.rest.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * User
 */
@Getter
@Setter
@Entity
public class User {

    @Id
    private String email;

    private String name;
    private String lastName;
    private String password;

    public User(String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

}