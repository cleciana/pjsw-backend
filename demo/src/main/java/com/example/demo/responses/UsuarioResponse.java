package com.example.demo.responses;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UsuarioResponse
 */
@Data
@Entity
@NoArgsConstructor
public class UsuarioResponse {

    private String name;
    
    @Id
    private String email;

    public UsuarioResponse(String name, String email) {
        this.name = name;
        this.email = email;
    }
}