package com.example.demo.rest.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;

/**
 * Profile
 */
@Data
@Entity
public class Perfil {

    private int id;
    private List<Integer> notas;
    private int likes;

    @OneToMany(mappedBy = "perfil")
    private Map<String, Comentario> comentários;

    public Perfil() {
        this.notas = new ArrayList<>();
        this.likes = 0;
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