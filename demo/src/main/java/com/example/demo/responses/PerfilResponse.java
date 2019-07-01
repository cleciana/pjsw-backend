package com.example.demo.responses;

import com.example.demo.rest.model.Disciplina;

/**
 * PerfilResponse
 */
public class PerfilResponse {

    private boolean deuLike;
    private int id;
    private int qtdLikes;

    public PerfilResponse(Disciplina disciplina, String username) {
        this.deuLike = disciplina.deuLike(username);
        this.qtdLikes = disciplina.getLikes().size();
    }

    public PerfilResponse() {
    }

    public boolean getDeuLike() {
        return this.deuLike;
    }

    public int getLikes() {
        return this.qtdLikes;
    }

    public void setDeuLike(boolean bo) {
        this.deuLike = bo;
    }

    public void setLikes(int ls) {
        this.qtdLikes = ls;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}