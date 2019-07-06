package com.example.demo.rest.model;

import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Disciplina
 */
@Data
@Entity
@NoArgsConstructor
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private HashSet<String> likes;
    private String description;
    private Boolean deuLike;
    private Integer qtdLikes;

    public Disciplina(String description) {
        this.likes = new HashSet<>();
        this.description = description;
    }

    /**
     * Adiciona um like a disciplina
     */
	public void like(String name) {
        if (this.likes == null) {
            this.likes = new HashSet<>();
        }
        if (this.likes.contains(name)) {
            this.likes.remove(name);
            this.deuLike(name);

        } else {
            this.likes.add(name);
        }
	}

	public void deuLike(String username) {
        this.setDeuLike(likes.contains(username));
        this.deuLike(name);
    }
}