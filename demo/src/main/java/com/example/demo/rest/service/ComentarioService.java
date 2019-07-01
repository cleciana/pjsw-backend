package com.example.demo.rest.service;

import com.example.demo.rest.dao.ComentarioDAO;
import com.example.demo.rest.model.Comentario;

import org.springframework.stereotype.Service;

/**
 * ComentarioService
 */
@Service
public class ComentarioService {

    private final ComentarioDAO<Comentario, Integer> comentarioDao;

    public ComentarioService(ComentarioDAO<Comentario, Integer> comentarioDAO) {
        this.comentarioDao = comentarioDAO;
    }

    public Comentario create(Comentario disciplina) {
        return this.comentarioDao.save(disciplina);
    }

    public Comentario findById(int id) {
        return this.comentarioDao.findById(id);
    }

}