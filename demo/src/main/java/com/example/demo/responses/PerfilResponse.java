package com.example.demo.responses;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.example.demo.rest.model.Comentario;
import com.example.demo.rest.model.Perfil;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PerfilResponse
 */
@Data
@Entity
@NoArgsConstructor
public class PerfilResponse {

    private boolean deuLike;

    @Id
    private int id;
    @OneToOne
    private Perfil perfil;
    private int qtdLikes;
    private ArrayList<Comentario> meus;

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