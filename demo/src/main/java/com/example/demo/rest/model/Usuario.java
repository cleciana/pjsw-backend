package com.example.demo.rest.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User
 */
@Data
@Entity
@NoArgsConstructor
public class Usuario {

    @Id
    private String email;

    private String name;
    private String lastName;
    private String password;

    public Usuario(String email, String name, String lastName, String password) {
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
    }

}