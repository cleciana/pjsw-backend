package com.example.demo.rest.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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

    @OneToMany
    private ArrayList<Comentario> comentarios;

    public Perfil() {
        this.notas = new ArrayList<>();
        this.likes = new HashSet<>();
        this.comentarios = new ArrayList<>();
    }

    public boolean getDeuLike(String username) {
		return this.likes.contains(username);
    }
    
	public void addComentario(String usuario, String texto) {
        Comentario comentario = new Comentario(usuario, texto);
        this.comentarios.add(comentario);
    }

    public List<Comentario> meusComentarios(String name) {
        List<Comentario> list = new ArrayList<>();

        for (Comentario c : this.comentarios) {
            if (c.getUsername().equals(name)) {
                list.add(c);
            }
        }
        return list;
    }

}