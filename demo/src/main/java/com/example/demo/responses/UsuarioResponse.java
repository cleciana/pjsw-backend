package com.example.demo.responses;

import javax.persistence.Entity;

import lombok.Data;

/**
 * UsuarioResponse
 */
@Data
@Entity
public class UsuarioResponse {

    private String name;
    private String email;

    public UsuarioResponse(String name, String email) {
        this.name = name;
        this.email = email;
    }
}