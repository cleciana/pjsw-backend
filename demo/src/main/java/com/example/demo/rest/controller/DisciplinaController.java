package com.example.demo.rest.controller;

import java.util.List;

import com.example.demo.exception.disciplina.ClassNotRegisteredException;
import com.example.demo.rest.model.Disciplina;
import com.example.demo.rest.model.Perfil;
import com.example.demo.rest.service.DisciplinaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassController
 */
@RestController
@RequestMapping({"v1/disciplinas"})
public class DisciplinaController {

    private DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService service) {
        this.disciplinaService = service;
    }

    /**
     * Retorna uma lista de todas as disciplinas cadastradas
     */
    @GetMapping("/all")
    public ResponseEntity<List<Disciplina>> getAll() {
        List<Disciplina> list = this.disciplinaService.getAll();
        return new ResponseEntity<List<Disciplina>>(list, HttpStatus.OK);
    }

    /**
     * Busca e retorna a disciplina pelo id
     * 
     * @param id
     *      Inteiro, identificador de uma disciplina
     * 
     * @return
     *      Retorna a disciplina identificada por id, se esta estiver cadastrada.
     *      Caso contrario lanca uma excecao
     */
    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Disciplina> findById(@PathVariable int id) {
        Disciplina auxClass = this.disciplinaService.findById(id);

        if (auxClass == null) {
            throw new ClassNotRegisteredException("Sorry, class does not exist.");
        }
        return new ResponseEntity<Disciplina>(auxClass, HttpStatus.OK);
    }

    /**
     * Retorna o perfil da disciplina identificada por {id}
     * 
     * @param id
     *          Identificador numérico de uma disciplina
     * 
     * @return
     *          Perfil com informacoes sobre a disciplina, como comentários, likes, notas, etc.
     */
    @GetMapping(value = "/{id}-perfil")
    @ResponseBody
    public ResponseEntity<Perfil> getProfile(@PathVariable int id) {
        Disciplina disciplina = this.disciplinaService.findById(id); 

        return new ResponseEntity<Perfil>(disciplina.getProfile(), HttpStatus.OK);
    }
}