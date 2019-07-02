package com.example.demo.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int disciplinaId;

    private Integer respComentarioId;
    private boolean deleted;
    private String username;
    private String content;

    public Comentario(String content) {
        this.content = content;
        this.deleted = false;
    }

    public boolean wasDeletd() {
        return this.deleted == true;
    }

}