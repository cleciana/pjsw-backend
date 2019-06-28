package com.example.demo.rest.controller;

import java.util.List;

import com.example.demo.exception.disciplina.ClassNotRegisteredException;
import com.example.demo.responses.PerfilResponse;
import com.example.demo.rest.model.Disciplina;
import com.example.demo.rest.model.Perfil;
import com.example.demo.rest.service.DisciplinaService;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;

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
     * Retorna o perfil da disciplina identificada por {id}
     * 
     * @param id Identificador numérico de uma disciplina
     * 
     * @return Perfil com informacoes sobre a disciplina, como comentários, likes,
     *         notas, etc.
     */
    @GetMapping(value = "/{id}-perfil")
    @ResponseBody
    public ResponseEntity<PerfilResponse> getProfile(@PathVariable int id) {
        Disciplina disciplina = this.disciplinaService.findById(id); 
        if (disciplina == null) {
            throw new ClassNotRegisteredException("Class does not exist.");
        }
        Perfil perfil = disciplina.getProfile();
        
        return new ResponseEntity<PerfilResponse>(new PerfilResponse(perfil, ), HttpStatus.OK);
    }

    /**
     * Busca as disciplinas que contem em seu nome a string recebida
     * @param string
     * @return
     */
    @GetMapping(value = "/search-{string}")
    public ResponseEntity<List<Disciplina>> getBysubString(@RequestBody String token, @PathVariable String string) {
        List<Disciplina> list = this.disciplinaService.findByName(string);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/{string}")
    @ResponseBody
    public ResponseEntity<List<Disciplina>> getByString(@RequestBody String string) {
        return null;
    }
}