package com.example.demo.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
    public ResponseEntity<PerfilResponse> getProfile(@PathVariable int id, HttpServletRequest request) {
        Disciplina disciplina = this.disciplinaService.findById(id); 
        if (disciplina == null) {
            throw new ClassNotRegisteredException("Class does not exist.");
        }
        Perfil perfil = disciplina.getProfile();
        String name = new LoginController().getTokenEmail(request);
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
    public ResponseEntity<List<Disciplina>> getBysubString(@RequestBody String token, @PathVariable String string) {
        List<Disciplina> list = this.disciplinaService.findByName(string);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

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

    /**
     * Adiciona um comentario a disciplina identificada por {id}
     */
    @PostMapping(value = "/{id}-comments")
    @ResponseBody
    public ResponseEntity<HttpStatus> addComentario(@PathVariable int id, String username, String content) {
        Disciplina disc = this.disciplinaService.findById(id);
        if (disc == null) {
            throw new ClassNotRegisteredException("Disciplina nao existe.");
        }
        disc.addComentario(username, content);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}