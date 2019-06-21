package com.example.demo.rest.service;

import java.util.List;

import com.example.demo.rest.dao.DisciplinaDAO;
import com.example.demo.rest.model.Disciplina;

import org.springframework.stereotype.Service;

/**
 * DisciplinaService
 */
@Service
public class DisciplinaService {

    private final DisciplinaDAO<Disciplina, Long> disciplinaDao;

    public DisciplinaService(DisciplinaDAO<Disciplina, Long> disciplinaDao) {
        this.disciplinaDao = disciplinaDao;
    }

     /**
     * Registra uma nova disciplina no banco de dados.
     * 
     * @param user Uma instancia de Disciplina
     * 
     * @return Retorna a Entidade Disciplina(Class)
     */
    public Disciplina create(Disciplina newClass) {
        return this.disciplinaDao.save(newClass);
    }

    public void delete(int id) {
        this.disciplinaDao.deleteById(id);
    }

    public Disciplina findById(int id) {
        return this.disciplinaDao.findById(id);
    }

	public List<Disciplina> getAll() {
       return this.disciplinaDao.findAll();
    }
    
}