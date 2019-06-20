package com.example.demo.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * Class, entidade que representa uma Disciplina
 */
@Data
@Entity
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private Perfil profile;

    public Disciplina(String description) {
        this.profile = new Perfil(description);
    }

}