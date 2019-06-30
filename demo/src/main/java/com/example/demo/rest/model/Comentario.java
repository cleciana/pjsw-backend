package com.example.demo.rest.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Comment
 */
@Data
@Entity
@NoArgsConstructor
public class Comentario {

    @Id
    private String username;
    
    private String content;

    public Comentario(String username, String content) {
        this.username = username;
        this.content = content;
    }
    
}