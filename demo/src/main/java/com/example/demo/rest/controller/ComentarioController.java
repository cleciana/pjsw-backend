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
@RequestMapping({ "vi/disciplinas/comentarios" })
public class ComentarioController {

    private ComentarioService comentarioService;
    private ModelMapper mapper;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
        this.mapper = new ModelMapper();
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