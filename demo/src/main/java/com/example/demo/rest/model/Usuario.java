package com.example.demo.rest.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * User
 */
@Data
@Entity
@AllArgsConstructor
public class Usuario {

    @Id
    private String email;

    private String login;
    private String name;
    private String lastName;
    private String password;

    public Usuario() {

    }

}