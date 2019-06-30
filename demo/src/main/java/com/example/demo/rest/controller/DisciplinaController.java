package com.example.demo.rest.controller;

import java.util.List;

import com.example.demo.exception.disciplina.ClassNotRegisteredException;
import com.example.demo.responses.PerfilResponse;
import com.example.demo.rest.model.Comentario;
import com.example.demo.rest.model.Disciplina;
import com.example.demo.rest.model.Perfil;
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
     * Retorna o perfil da disciplina identificada por {id}
     * 
     * @param id Identificador numérico de uma disciplina
     * 
     * @return Perfil com informacoes sobre a disciplina, como comentários, likes,
     *         notas, etc.
     */
    @GetMapping(value = "/{id}-perfil")
    @ResponseBody
    public ResponseEntity<PerfilResponse> getProfile(@PathVariable int id, @RequestHeader("Authorization") String token) {
        Disciplina disciplina = this.disciplinaService.findById(id); 
        if (disciplina == null) {
            throw new ClassNotRegisteredException("Disciplina nao existe.");
        }
        Perfil perfil = disciplina.getProfile();
        String name = new LoginController().getTokenEmail(token);
        return new ResponseEntity<PerfilResponse>(new PerfilResponse(perfil, name), HttpStatus.OK);
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
    public ResponseEntity<List<Disciplina>> getBysubString(@RequestHeader("Authorization") String token, @PathVariable String string) {
        String name = new LoginController().getTokenEmail(token);
        if (name != null) {
            List<Disciplina> list = this.disciplinaService.findByName(string);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Disciplina>>(HttpStatus.OK);
        }
       
    }

    /**
     * 
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}-comments")
    @ResponseBody
    public ResponseEntity<List<Comentario>> getComentarios(@PathVariable int id) {
        Disciplina disc = this.disciplinaService.findById(id);
        if (disc == null) {
            throw new ClassNotRegisteredException("Disciplina nao existe.");
        }
        List<Comentario> com = disc.getComentarios();

        return new ResponseEntity<>(com, HttpStatus.OK);
    }

    @PostMapping(value = "/{id}-perfil/like")
    @ResponseBody
    public ResponseEntity<PerfilResponse> like(@PathVariable int id, @RequestHeader("Authorization") String token) {
        Disciplina disc = this.disciplinaService.findById(id);
        if (disc == null) {
            throw new ClassNotRegisteredException("Disciplina nao existe.");
        }
        String name = new LoginController().getTokenEmail(token);
        disc.like(name);

        return new ResponseEntity<>(new PerfilResponse(disc.getProfile(), name) ,HttpStatus.OK);
    }

    /**
     * Adiciona um comentario a disciplina identificada por {id}
     */
    @PostMapping(value = "/{id}-comments")
    @ResponseBody
    public ResponseEntity<Comentario> addComentario(@PathVariable int id, @RequestBody Comentario comentario) {
        Disciplina disc = this.disciplinaService.findById(id);
        if (disc == null) {
            throw new ClassNotRegisteredException("Disciplina nao existe.");
        }
        disc.addComentario(comentario);

        return new ResponseEntity<>(comentario, HttpStatus.CREATED);
    }

    @PostMapping(value = "/")
    @ResponseBody
    public ResponseEntity<Disciplina> create(@RequestBody Disciplina disciplina) {
        Disciplina disciplina2 = this.disciplinaService.findByDescription(disciplina.getDescription());

        if (disciplina2 == null) {
            Disciplina d = this.disciplinaService.create(disciplina);
            if (d == null) {
                throw new InternalError("Ops, algo deu errado.");
            }
            return new ResponseEntity<>(d, HttpStatus.CREATED);
        } else {
            throw new ClassNotRegisteredException("Disciplina ja existe.");
        }
    }
}