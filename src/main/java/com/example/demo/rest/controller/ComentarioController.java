package com.example.demo.rest.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.exception.UnauthorizedAccessException;
import com.example.demo.exception.comment.CommentNotFoundException;
import com.example.demo.rest.dto.ComentarioDTO;
import com.example.demo.rest.model.Comentario;
import com.example.demo.rest.service.ComentarioService;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping({ "v1/comentarios" })
@Api(value = "Controller de Comentarios", description = "Recebe e mapeia as requisições relativas a comentários de disciplinas.")
public class ComentarioController {

    private ComentarioService comentarioService;
    private ModelMapper mapper;
    private LoginController login;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
        this.mapper = new ModelMapper();
        this.login = new LoginController();
    }
    
    //@CrossOrigin
    @ApiOperation(value = "Adiciona um comentario a disciplina identificada por id")
    @PostMapping(value = "/")
    @ResponseBody
    public ResponseEntity<ComentarioDTO> createComentario(@RequestHeader("Authorization") String token, @RequestBody int id, @RequestBody Comentario comentario) {
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

    //@CrossOrigin
    @ApiOperation(value = "Marca um comentario como deletado")
    @DeleteMapping(value = "/")
    @ResponseBody
    public ResponseEntity<ComentarioDTO> delete(@RequestBody int id) {
        Comentario com = this.comentarioService.findById(id);
        if (com == null) throw new CommentNotFoundException("O Comentario nao existe.");
        com.setDeleted(true);
        Comentario comentario = this.comentarioService.update(com);
        return new ResponseEntity<>(mapper.map(comentario, ComentarioDTO.class), HttpStatus.OK);
    }

    //@CrossOrigin
    @ApiOperation(value = "Retorna um objeto que representa um comentario, caso o comentario tenha sido deletado anteriormente, retorna um objeto com texto conteúdo vazio.")
    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<ComentarioDTO> getComentario(@RequestBody int id) {
        Comentario com = this.comentarioService.findById(id);
        if (com == null) {
            throw new CommentNotFoundException("Desculpe, este comentario não existe.");
        }
        ComentarioDTO comentario = mapper.map(com, ComentarioDTO.class);
        if (com.wasDeletd()) {
            comentario.setContent("");
            return new ResponseEntity<>(comentario, HttpStatus.OK);
        }
        return new ResponseEntity<>(comentario, HttpStatus.OK);
    }

    //@CrossOrigin
    @ApiOperation(value = "Busca e retorna todos os comentarios de uma determinada disciplina")
    @GetMapping(value = "/")
    @ResponseBody
    public ResponseEntity<List<ComentarioDTO>> getComentarios(@RequestBody int id) {
        List<ComentarioDTO> cDtos = new ArrayList<>();
        List<Comentario> comentarios = this.comentarioService.findByDisciplinaId();
        for (Comentario c : comentarios) {
            cDtos.add(mapper.map(c, ComentarioDTO.class));
        }
        return new ResponseEntity<>(cDtos, HttpStatus.OK);
    }
}