package com.example.demo.rest.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.Data;

/**
 * Profile
 */
@Data
@Entity
public class Perfil {

    @Id
    private int id;

    private int likes;

    @OneToMany
    private Map<String, Comentario> comentários;

    public Perfil() {
        this.likes = 0;
        this.comentários = new HashMap<>();
    }

    public void addComentario(String usuario, String texto) {
        Comentario comentario = new Comentario(usuario, texto);
        this.comentários.put(usuario, comentario);
    }

    public ResponseEntity<List<Comentario>> meusComentarios(@RequestBody String name) {
        List<Comentario> list = new ArrayList<>();

        for (Comentario c : this.comentários.values()) {
            if (c.getUsername().equals(name)) {
                list.add(c);
            }
        }
        return new ResponseEntity<List<Comentario>>(list, HttpStatus.OK);
    }
}