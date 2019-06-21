package com.example.demo.rest.model;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Comment
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Comentario {

    private String username;
    private String content;

    
    
}