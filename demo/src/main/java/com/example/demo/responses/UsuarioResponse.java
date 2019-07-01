package com.example.demo.responses;

import lombok.Data;

/**
 * UsuarioResponse
 */
@Data
public class UsuarioResponse {

    private String name;
    private String email;

    public UsuarioResponse(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UsuarioResponse() {
    }
}