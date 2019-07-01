package com.example.demo.responses;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.example.demo.rest.model.Comentario;
import com.example.demo.rest.model.Disciplina;

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

    private int qtdLikes;
    private ArrayList<Comentario> meus;

    public PerfilResponse(Disciplina disciplina, String username) {
        this.setDeuLike(disciplina.deuLike(username));
        this.qtdLikes = disciplina.getLikes().size();
        // comentarios que o usuario deu na disciplina
    }

}