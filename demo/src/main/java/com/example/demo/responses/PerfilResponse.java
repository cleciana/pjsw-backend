package com.example.demo.responses;

import java.util.List;

import javax.persistence.Entity;

import com.example.demo.rest.model.Comentario;
import com.example.demo.rest.model.Perfil;

import lombok.Data;

/**
 * PerfilResponse
 */
@Data
@Entity
public class PerfilResponse {

    private boolean deuLike;
    private Perfil perfil;
    private int qtdLikes;
    private List<Comentario> meus;

    public PerfilResponse(Perfil perfil, String username) {
        this.perfil = perfil;
        this.setDeuLike(this.userDeuLike(username));
        this.qtdLikes = this.getPerfil().getLikes().size();
        this.meus = perfil.meusComentarios(username);
    }

    private boolean userDeuLike(String username) {
        return this.perfil.getDeuLike(username);
    }
}