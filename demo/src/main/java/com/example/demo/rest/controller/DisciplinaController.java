package com.example.demo.rest.controller;

import java.util.List;

import com.example.demo.exception.UnauthorizedAccessException;
import com.example.demo.exception.disciplina.ClassNotRegisteredException;
import com.example.demo.responses.PerfilResponse;
import com.example.demo.rest.model.Disciplina;
import com.example.demo.rest.service.DisciplinaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
     * Cadastra uma disciplina no sistema
     * 
     * @param disciplina
     * @return
     */
    @PostMapping(value = "/")
    @ResponseBody
    public ResponseEntity<HttpStatus> create(@RequestHeader("Authorization") String token,
            @RequestBody Disciplina disciplina) {
        Disciplina disciplina2 = this.disciplinaService.findByDescription(disciplina.getDescription());

        if (disciplina2 == null) {
            String name = new LoginController().getTokenEmail(token);
            if (name != null) {
                Disciplina d = this.disciplinaService.create(disciplina);
                if (d == null) {
                    throw new InternalError("Ops, algo deu errado.");
                }
            } else {
                throw new UnauthorizedAccessException("Você não tem permissão. Por favor, faça login.");
            }

        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Retorna um objeto perfil que contem informacoes da disciplina identificada
     * por {id}
     * 
     * @param id Identificador numérico de uma disciplina
     * 
     * @return Perfil com informacoes sobre a disciplina, como comentários, likes,
     *         notas, etc.
     */
    @GetMapping(value = "/{id}-perfil")
    @ResponseBody
    public ResponseEntity<PerfilResponse> getProfile(@PathVariable int id,
            @RequestHeader("Authorization") String token) {
        Disciplina disciplina = this.disciplinaService.findById(id);
        if (disciplina == null) {
            throw new ClassNotRegisteredException("Disciplina nao existe.");
        }
        String name = new LoginController().getTokenEmail(token);
        if (name != null) {
            PerfilResponse response = new PerfilResponse(disciplina, name);
            return new ResponseEntity<PerfilResponse>(response, HttpStatus.OK);

        } else {
            throw new UnauthorizedAccessException("Você não tem permissão. Por favor, faça login.");
        }
    }

    /**
     * Busca as disciplinas que contem em seu nome a string recebida.
     * 
     * @param string String recebida do usuario.
     * 
     * @return Retorna uma lista de disciplinas que contem o parametro string.
     */
    @GetMapping(value = "/search-{string}")
    @ResponseBody
    public ResponseEntity<List<Disciplina>> getBysubString(@PathVariable String string) {
        List<Disciplina> list = this.disciplinaService.findByName(string);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "/{id}-like")
    @ResponseBody
    public ResponseEntity<PerfilResponse> like(@PathVariable int id, @RequestHeader("Authorization") String token) {
        Disciplina disc = this.disciplinaService.findById(id);
        if (disc == null) {
            throw new ClassNotRegisteredException("Disciplina nao existe.");
        }
        String name = new LoginController().getTokenEmail(token);
        if (name != null) {
            disc.like(name);
            return new ResponseEntity<>(new PerfilResponse(disc, name), HttpStatus.OK);

        } else {
            throw new UnauthorizedAccessException("Você não tem permissão. Por favor, faça login.");
        }
    }

}