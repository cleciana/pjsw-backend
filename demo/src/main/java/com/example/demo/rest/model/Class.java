package com.example.demo.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * Class, entidade que representa uma Disciplina
 */
@Entity
@Data
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private Profile profile;
    private String description;

    public Class(String description) {
        this.description = description;
    }

}