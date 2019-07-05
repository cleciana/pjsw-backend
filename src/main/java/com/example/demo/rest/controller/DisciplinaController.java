package com.example.demo.rest.controller;

import java.util.List;

import com.example.demo.exception.UnauthorizedAccessException;
import com.example.demo.exception.disciplina.ClassNotRegisteredException;
import com.example.demo.rest.dto.DisciplinaDTO;
import com.example.demo.rest.model.Disciplina;
import com.example.demo.rest.service.DisciplinaService;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping({ "v1/disciplinas" })
@Api(value = "Controller de Disciplinas", description = "Recebe as requisições relacionadas a discipinas e as mapeia para o servico responsavel.")
public class DisciplinaController {

    private DisciplinaService disciplinaService;
    private LoginController loginController;
    private ModelMapper mapper;

    public DisciplinaController(DisciplinaService service) {
        this.disciplinaService = service;
        this.loginController = new LoginController();
        this.mapper = new ModelMapper();
    }

    @CrossOrigin
    @ApiOperation(value = "Cadastra uma nova disciplina no sistema.")
    @PostMapping(value = "/")
    @ResponseBody
    public ResponseEntity<DisciplinaDTO> create(@RequestHeader("Authorization") String token,
            @RequestBody Disciplina disciplina) {
        Disciplina disciplina2 = this.disciplinaService.findByDescription(disciplina.getDescription());

        if (disciplina2 == null) {
            String name = loginController.getTokenEmail(token);

            if (name != "" && name == "ADMIN") {
                Disciplina d = this.disciplinaService.create(disciplina);

                if (d == null) {
                    throw new InternalError("Ops, algo deu errado.");
                }
            } else {
                throw new UnauthorizedAccessException("Voce nao tem permissao. Por favor, faca login.");
            }
        }
        return new ResponseEntity<>(mapper.map(disciplina, DisciplinaDTO.class), HttpStatus.CREATED);
    }

    @CrossOrigin
    @ApiOperation(value = "Busca e retorna uma Disciplina, identificada por um {id}")
    @GetMapping(value = "/{id}perfil")
    @ResponseBody
    public ResponseEntity<DisciplinaDTO> getDisciplina(@PathVariable int id, @RequestHeader("Authorization") String token) {
        Disciplina disciplina2 = this.disciplinaService.findById(id);
        String userEmail = loginController.getTokenEmail(token);

        if (userEmail == "") {
            throw new UnauthorizedAccessException("Voce nao tem permissao. Por favor, faca login.");
        }
        if (disciplina2 == null) {
            throw new ClassNotRegisteredException("Disciplina nao existe.");
        }
        return new ResponseEntity<>(mapper.map(disciplina2, DisciplinaDTO.class), HttpStatus.OK);
    }

    @CrossOrigin
    @ApiOperation(value = "Busca as disciplinas que contem em seu nome a string recebida.")
    @GetMapping(value = "/{string}")
    @ResponseBody
    public ResponseEntity<List<Disciplina>> getBysubString(@PathVariable String string) {
        List<Disciplina> list = this.disciplinaService.findByName(string);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @CrossOrigin
    @ApiOperation(value = "Busca e retorna uma listagem de disciplinas ordenadas por quantidade de likes, de forma decrescente.")
    @GetMapping(value = "/1")
    @ResponseBody
    public ResponseEntity<List<Disciplina>> rank1(@RequestHeader("Authorization") String token) {
        String userMail = loginController.getTokenEmail(token);

        if (userMail == "") {
            throw new UnauthorizedAccessException("Voce nao tem permissao. Por favor, faca login.");
        }
        List<Disciplina> lista = this.disciplinaService.findAllByLikes();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @CrossOrigin
    @ApiOperation(value = "Adiciona um like a disciplina identificada por {id}")
    @PutMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<DisciplinaDTO> likeDisciplina(@PathVariable int id, @RequestHeader("Authorization") String token) {
        Disciplina disciplina2 = this.disciplinaService.findById(id);
        String userEmail = loginController.getTokenEmail(token);

        if (userEmail == "") {
            throw new UnauthorizedAccessException("Voce nao tem permissao. Por favor, faca login.");
        }
        if (disciplina2 == null) {
            throw new ClassNotRegisteredException("Disciplina nao existe.");
        }
        disciplina2.like(userEmail);
        Disciplina disciplinaa = this.disciplinaService.update(disciplina2);
        return new ResponseEntity<DisciplinaDTO>(mapper.map(disciplinaa, DisciplinaDTO.class), HttpStatus.OK);
    }
}