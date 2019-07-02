package com.example.demo.rest.dao;

import java.util.List;

import com.example.demo.rest.model.Disciplina;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * ClassDAO
 */
@Repository
public interface DisciplinaDAO<T, ID> extends JpaRepository<Disciplina, Integer> {

    Disciplina save(Disciplina aClass);

    Disciplina findById(int id);

	List<Disciplina> findByDescriptionIgnoreCaseContaining(String description);

    @Query(value = "Select d from Disciplina d where d.description = :description")
    Disciplina findByDescription(@Param("description") String description);

    List<Disciplina> findAllOrderByqtdLikesAsc();
}