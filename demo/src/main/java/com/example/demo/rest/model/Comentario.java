package com.example.demo.rest.model;

import javax.persistence.Entity;
import javax.persistence.Id;

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

    @Id
    private String username;
    
    private String content;

    
    
}