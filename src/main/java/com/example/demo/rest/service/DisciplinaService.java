package com.example.demo.rest.service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.rest.dao.DisciplinaDAO;
import com.example.demo.rest.model.Disciplina;

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
    
    public List<Disciplina> findDisciplinas() {
        List<Disciplina> list = this.disciplinaDao.findAll();

        return this.sortDisciplinas(list);
    }

    private List<Disciplina> sortDisciplinas(List<Disciplina> list) {
        Disciplina[] disciplinas = this.meuToArray(list, list.size());
        this.likeSort(disciplinas, 0, list.size());

        List<Disciplina> nova = new ArrayList<>();
        for (int i = disciplinas.length-1; i >= 0; i--) {
            nova.add(disciplinas[i]);
        }
        return nova;
    }

    private Disciplina[] meuToArray(List<Disciplina> list, int n) {
        Disciplina[] array = new Disciplina[n];
        for (int i = 0; i < n; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    private void likeSort(Disciplina[] disciplinas, int left, int right) {
        if (left < right) {
            int index = partition(disciplinas, left, right);

            likeSort(disciplinas, left, index - 1);
            likeSort(disciplinas, index + 1, right);
        }
    }

    private int partition(Disciplina[] disciplinas, int left, int right) {
        Disciplina pivo = disciplinas[right];
        int i = left -1;

        for (int j = left; j < right; j++) {
            if (disciplinas[j].getLikes().size() <= pivo.getLikes().size()) {
                i++;
                Disciplina aux = disciplinas[i];
                disciplinas[i] = disciplinas[j];
                disciplinas[j] = aux;
            }
        }
        Disciplina aux2 = disciplinas[i+1];
        disciplinas[i+1] = disciplinas[right];
        disciplinas[right] = aux2;
        return i+1;
    }

    public List<Disciplina> findByComments() {
        return this.disciplinaDao.OrderByComentariosDesc();
    }
}