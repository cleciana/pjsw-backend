package com.example.demo.rest.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * Profile
 */
@Data
@Entity
public class Perfil {

    @Id
    private String description;

    private List<Integer> notas;
    private Map<String, Integer> likes;
    private Map<String, Comentario> comentários;

    public Perfil(String description) {
        this.description = description;
        this.notas = new ArrayList<>();
        this.likes = new HashMap<>();
       this.comentários = new HashMap<>();
    }

    public double getMean() {
        return this.sum() / notas.size();
    }

    private int sum() {
        int soma = 0;
        for (int nota : notas) {
            soma += nota;
        }
        return soma;
    }
}