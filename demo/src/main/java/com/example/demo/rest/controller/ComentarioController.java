package com.example.demo.rest.controller;

import com.example.demo.exception.UnauthorizedAccessException;
import com.example.demo.exception.comment.CommentNotFoundException;
import com.example.demo.responses.ComentarioResponse;
import com.example.demo.rest.model.Comentario;
import com.example.demo.rest.service.ComentarioService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ComentarioController
 */
@RestController
@RequestMapping({ "vi/disciplinas/comentarios" })
public class ComentarioController {

    private ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }
    
    /**
     * Adiciona um comentario a disciplina identificada por {id}
     */
    @PostMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Comentario> addComentario(@RequestHeader("Authorization") String token, @PathVariable int id, @RequestBody Comentario comentario) {
        Comentario com = this.comentarioService.findById(comentario.getId());
        if (com == null) {
            String name = new LoginController().getTokenEmail(token);
            if (name != null) {
                comentario.setDisciplinaId(id);
                this.comentarioService.create(comentario);

            }   else {
                throw new UnauthorizedAccessException("Você não tem permissão. Por favor, faça login.");
            }
        }
        return new ResponseEntity<>(comentario, HttpStatus.CREATED);
    }

    /**
     * Marca um comentario como deletado
     */
    @DeleteMapping(value = "/")
    @ResponseBody
    public ResponseEntity<ComentarioResponse> delete(@PathVariable int id) {
        Comentario com = this.comentarioService.findById(id);
        com.setDeleted(true);
        return new ResponseEntity<>(new ComentarioResponse(com.getUsername(), ""), HttpStatus.OK);
    }

    /**
     * Retorna um objeto que representa um comentario, caso o comentario tenha sido deletado
     * anteriormente, retorna um objeto com texto conteúdo vazio.
     * 
     * @param id    Identificador numerico de comentario
     */
    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<ComentarioResponse> getComentario(@PathVariable int id) {
        Comentario com = this.comentarioService.findById(id);
        if (com == null) {
            throw new CommentNotFoundException("Desculpe, este comentario não existe.");
        }
        String name = com.getUsername();
        if (com.isDeleted()) {
            return new ResponseEntity<>(new ComentarioResponse(name, ""), HttpStatus.OK);
        }
        ComentarioResponse res = new ComentarioResponse(name, com.getContent());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}