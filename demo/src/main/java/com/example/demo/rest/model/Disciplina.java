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

    public Disciplina(String description) {
        this.likes = new HashSet<>();
        this.description = description;
    }

    /**
     * Adiciona um like a disciplina
     */
	public void like(String name) {
        this.likes.add(name);
	}

	public boolean deuLike(String username) {
		return this.likes.contains(username);
	}
}