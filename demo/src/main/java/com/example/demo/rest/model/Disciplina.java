package com.example.demo.rest.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

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

    @OneToOne
    @MapsId
    private Perfil profile = new Perfil();

    private String description;

    public Disciplina(String description) {
        this.profile = new Perfil();
        this.description = description;
    }

    /**
     * Adiciona um comentario a essa disciplina.
     * 
     * @param usuario   Nome do usuario que escreveu o comentario.
     * 
     * @param texto     Conteudo do comentario.
     */
    public void addComentario(Comentario comentario) {
        this.profile.addComentario(comentario);
    }

    /**
     * Retorna uma Lista de Comentarios.
     * 
     * @return  List<Comentarios> comentarios feitos sobre essa disciplina.
     */
	public List<Comentario> getComentarios() {
        return this.profile.getComentarios();
	}
    /**
     * Adiciona um like a disciplina
     */
	public void like(String name) {
        this.getProfile().like(name);
	}
}