package com.example.demo.rest.controller;

import com.example.demo.exception.UnauthorizedAccessException;
import com.example.demo.exception.comment.CommentNotFoundException;
import com.example.demo.dto.ComentarioDTO;
import com.example.demo.rest.model.Comentario;
import com.example.demo.rest.service.ComentarioService;

import org.modelmapper.ModelMapper;
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
@RequestMapping({ "v1/comentarios" })
public class ComentarioController {

    private ComentarioService comentarioService;
    private ModelMapper mapper;
    private LoginController login;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
        this.mapper = new ModelMapper();
        this.login = new LoginController();
    }
    
    /**
     * Adiciona um comentario a disciplina identificada por {id}
     */
    @PostMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<ComentarioDTO> addComentario(@RequestHeader("Authorization") String token, @PathVariable int id, @RequestBody Comentario comentario) {
        String userString = this.login.getTokenEmail(token);

        if (userString == "") {
            throw new UnauthorizedAccessException("Voce nao tem permissao. Por favor, faca login.");
        }
        comentario.setUsername(userString);
        comentario.setDisciplinaId(id);
        Comentario com = this.comentarioService.create(comentario);

        if (com == null) {
            throw new InternalError("Ops, algo deu errado.");
        }
        return new ResponseEntity<ComentarioDTO>(mapper.map(comentario, ComentarioDTO.class), HttpStatus.CREATED);
    }

    /**
     * Marca um comentario como deletado
     */
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<ComentarioDTO> delete(@PathVariable int id) {
        Comentario com = this.comentarioService.findById(id);
        com.setDeleted(true);
        return new ResponseEntity<>(mapper.map(com, ComentarioDTO.class), HttpStatus.OK);
    }

    /**
     * Retorna um objeto que representa um comentario, caso o comentario tenha sido deletado
     * anteriormente, retorna um objeto com texto conteúdo vazio.
     * 
     * @param id    Identificador numerico de comentario
     */
    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<ComentarioDTO> getComentario(@PathVariable int id) {
        Comentario com = this.comentarioService.findById(id);
        if (com == null) {
            throw new CommentNotFoundException("Desculpe, este comentario não existe.");
        }
        ComentarioDTO comentario = mapper.map(com, ComentarioDTO.class);
        if (com.isDeleted()) {
            comentario.setContent("");
            return new ResponseEntity<>(comentario, HttpStatus.OK);
        }
        return new ResponseEntity<>(comentario, HttpStatus.OK);
    }
}