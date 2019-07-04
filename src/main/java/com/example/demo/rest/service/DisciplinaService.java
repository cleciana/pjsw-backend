package com.example.demo.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.example.demo.rest.dao.DisciplinaDAO;
import com.example.demo.rest.model.Disciplina;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import main.java.com.example.demo.rest.dto.ContComentariosDTO;

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
        return this.disciplinaDao.findAll(new Sort(Sort.Direction.DESC, "qtdLikes"));
    }

    public List<ContComentariosDTO> findByComments() {
        List<Disciplina[]> lista = this.disciplinaDao.findComentarios();
        /*
        List<ContComentariosDTO> contagem = new ArrayList<>();

        ModelMapper mapper = new ModelMapper();
        for (Disciplina[] dupla : lista) {
            ContComentariosDTO novo = mapper.map(new ContComentariosDTO(dupla[0], dupla[1]), ContComentariosDTO.class);
            contagem.add(novo);
        */
        return lista;
    }
}