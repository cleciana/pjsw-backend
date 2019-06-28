package com.example.demo.rest.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.example.demo.rest.model.Disciplina;

/**
 * DisciplinaCustomRepositoryImpl
 */
public class DisciplinaCustomRepositoryImpl implements DisciplinaCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Disciplina> findByString(List<String> descriptions) {
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Disciplina> query = cBuilder.createQuery(Disciplina.class);
        Root<Disciplina> dRoot = query.from(Disciplina.class);

        Path<String> namePath = dRoot.get("description");

        List<Predicate> predicates = new ArrayList<>();
        for (String description : descriptions) {
            predicates.add(cBuilder.like(namePath, description));
        }
        query.select(dRoot).where(cBuilder.or(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query).getResultList();

    }

}