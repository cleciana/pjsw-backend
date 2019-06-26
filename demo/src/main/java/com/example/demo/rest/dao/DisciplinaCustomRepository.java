package com.example.demo.rest.dao;

import java.util.List;
import java.util.Set;

import com.example.demo.rest.model.Disciplina;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * DisciplinaCustomRepository
 */
public interface DisciplinaCustomRepository {

    // Busca as disciplinas que contem em sua descricao uma substring recebida do
    // usuario
    @Query("SELECT d FROM Disciplina WHERE d.description IN :descriptions")
    List<Disciplina> findByString(@Param("descriptions") List<String> descriptions);
}