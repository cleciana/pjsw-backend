package com.example.demo.rest.model;

import java.util.ArrayList;
import java.util.HashSet;

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
    private int id;

    private ArrayList<Integer> notas;

    private HashSet<String> likes;

    private ArrayList<Comentario> comentarios;

    public Perfil() {
        this.notas = new ArrayList<>();
        this.likes = new HashSet<>();
        this.comentarios = new ArrayList<>();
    }

    public boolean getDeuLike(String username) {
		return this.likes.contains(username);
    }
    
	public void addComentario(Comentario comentario) {
        this.comentarios.add(comentario);
    }

    public ArrayList<Comentario> meusComentarios(String name) {
        ArrayList<Comentario> list = new ArrayList<>();

        for (Comentario c : this.comentarios) {
            if (c.getUsername().equals(name)) {
                list.add(c);
            }
        }
        return list;
    }

	public void like(String name) {
        if (this.likes.contains(name)) {
            return;
        }
        this.likes.add(name);
	}

}