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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassController
 */
@RestController
@RequestMapping({ "v1/disciplinas" })
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
     * Retorna o perfil da disciplina identificada por {id}
     * 
     * @param id Identificador numérico de uma disciplina
     * 
     * @return Perfil com informacoes sobre a disciplina, como comentários, likes,
     *         notas, etc.
     */
    @GetMapping(value = "/{id}-perfil")
    @ResponseBody
    public ResponseEntity<Perfil> getProfile(@PathVariable int id) {
        Disciplina disciplina = this.disciplinaService.findById(id);
        if (disciplina == null) {
            throw new ClassNotRegisteredException("Class does not exist.");
        }
        return new ResponseEntity<Perfil>(disciplina.getProfile(), HttpStatus.OK);
    }

    @GetMapping(value = "/{string}")
    @ResponseBody
    public ResponseEntity<List<Disciplina>> getByString(@RequestBody String string) {
        return null;
    }
}