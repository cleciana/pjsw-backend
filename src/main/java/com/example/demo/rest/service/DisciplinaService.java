package com.example.demo.rest.service;

import java.util.List;

import com.example.demo.rest.dao.DisciplinaDAO;
import com.example.demo.rest.model.Disciplina;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * DisciplinaService
 */
@Service
public class DisciplinaService {

    private final DisciplinaDAO<Disciplina, Integer> disciplinaDao;

    public DisciplinaService(DisciplinaDAO<Disciplina, Integer> disciplinaDao) {
        this.disciplinaDao = disciplinaDao;
    }

     /**
     * Registra uma nova disciplina no banco de dados.
     * 
     * @param user Uma instancia de Disciplina
     * 
     * @return Retorna a Entidade Disciplina
     */
    public Disciplina create(Disciplina disciplina) {
        return this.disciplinaDao.save(disciplina);
    }

    public Disciplina findById(int id) {
        return this.disciplinaDao.findById(id);
    }

	public List<Disciplina> findByName(String name) {
		return this.disciplinaDao.findByDescriptionIgnoreCaseContaining(name);
	}
    
    public Disciplina findByDescription(String string) {
        return this.disciplinaDao.findByDescription(string);
    }

	public Disciplina update(Disciplina disciplina) {
        return this.disciplinaDao.save(disciplina);
    }
    
    public List<Disciplina> findAllByLikes() {
        List<Disciplina> list = this.disciplinaDao.findAll(); //new Sort(Sort.Direction.DESC, "qtdLikes")
        for (Disciplina d : list) {
            for (String n : d.getLikes()) {
                if (n == null) {
                    d.getLikes().remove(n);
                }
            }
        }
        return list;
    }

    public List<Disciplina> findByComments() {
        return this.disciplinaDao.OrderByComentariosDesc();
    }
}