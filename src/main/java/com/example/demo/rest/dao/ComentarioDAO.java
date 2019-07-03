package com.example.demo.rest.dao;

import com.example.demo.rest.model.Comentario;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ComentarioDAO
 */
public interface ComentarioDAO<T, ID> extends JpaRepository<Comentario, Integer> {

    Comentario save(Comentario comentario);

    Comentario findById(int id);
    
}