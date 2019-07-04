package com.example.demo.rest.controller;

import java.util.List;

import com.example.demo.exception.UnauthorizedAccessException;
import com.example.demo.exception.disciplina.ClassNotRegisteredException;
import com.example.demo.rest.dto.DisciplinaDTO;
import com.example.demo.rest.model.Disciplina;
import com.example.demo.rest.service.DisciplinaService;

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

/**
 * ClassController
 */
@RestController
@RequestMapping({ "v1/disciplinas" })
public class DisciplinaController {

    private DisciplinaService disciplinaService;
    private LoginController loginController;
    private ModelMapper mapper;

    public DisciplinaController(DisciplinaService service) {
        this.disciplinaService = service;
        this.loginController = new LoginController();
        this.mapper = new ModelMapper();
    }

    /**
     * Cadastra uma disciplina no sistema
     * 
     * @param disciplina
     * @return
     */
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


    /**
     * Busca as disciplinas que contem em seu nome a string recebida.
     * 
     * @param string String recebida do usuario.
     * 
     * @return Retorna uma lista de disciplinas que contem o parametro string.
     */
    @GetMapping(value = "/{string}")
    @ResponseBody
    public ResponseEntity<List<Disciplina>> getBysubString(@PathVariable String string) {
        List<Disciplina> list = this.disciplinaService.findByName(string);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    
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

    @GetMapping(value = "/2")
    @ResponseBody
    public ResponseEntity<List<Disciplina>> rank2(@RequestHeader("Authorization") String token) {
        String userMail = loginController.getTokenEmail(token);

        if (userMail == "") {
            throw new UnauthorizedAccessException("Voce nao tem permissao. Por favor, faca login.");
        }
        List<Disciplina> lista = this.disciplinaService.findByComments();
        lista.sort(new ComentarioComparator());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

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