package com.example.demo.rest.service;

import java.util.List;

import com.example.demo.rest.dao.ClassDAO;
import com.example.demo.rest.model.Class;

import org.springframework.stereotype.Service;

/**
 * ClassService
 */
@Service
public class ClassService {

    private final ClassDAO<Class, Long> classDao;

    public ClassService(ClassDAO<Class, Long> classDao) {
        this.classDao = classDao;
    }

     /**
     * Registra uma nova disciplina no banco de dados.
     * 
     * @param user Uma instancia de Disciplina
     * 
     * @return Retorna a Entidade Disciplina(Class)
     */
    public Class create(Class newClass) {
        return this.classDao.save(newClass);
    }

    public void delete(int id) {
        this.classDao.deleteById(id);
    }

    public Class findById(int id) {
        return this.classDao.findById(id);
    }

    public List<Class> getAllContains(String string) {
        // continua nos prox capitulos
        return null;
    }
}