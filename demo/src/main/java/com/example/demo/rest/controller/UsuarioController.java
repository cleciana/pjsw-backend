package com.example.demo.rest.controller;

import java.util.Optional;

import com.example.demo.exception.InvalidFieldException;
import com.example.demo.exception.user.UserNotFoundException;
import com.example.demo.rest.model.Usuario;
import com.example.demo.rest.service.UsuarioService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController, direciona as requisições para o UserService
 */
@RestController
@RequestMapping({ "/v1/user" })
public class UsuarioController {

    private UsuarioService userService;

    public UsuarioController(UsuarioService service) {
        this.userService = service;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Usuario> findById(@PathVariable String id) {
        Optional<Usuario> user = this.userService.findById(id);

        if (!user.isPresent()) {
            throw new UserNotFoundException("User not found.");
        }
        return new ResponseEntity<Usuario>(user.get(), HttpStatus.OK);
    }

    /**
     * Registra um novo usuario no banco de dados
     * @param user
     *      Instancia de Usuario
     * @return
     */
    @PostMapping(value = "/")
    @ResponseBody
    public ResponseEntity<Usuario> create(@RequestBody Usuario user) {

        Optional<Usuario> aux = userService.findById(user.getEmail());
        if (aux.isPresent()) {
            throw new InvalidFieldException("User already registered.");
        }
        Usuario newUser = userService.create(user);
        if (newUser == null) {
            throw new InternalError("Something went wrong :/");
        }
        return new ResponseEntity<Usuario>(newUser, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Usuario> delete(@PathVariable String id) {
        try {
            userService.delete(id);
            return new ResponseEntity<Usuario>(HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalError("Something went wrong :/");
        }
    }

    @PutMapping(value = "/")
    public ResponseEntity<Usuario> update(@RequestBody Usuario user) {
        try {
            Usuario updated = userService.update(user);
            return new ResponseEntity<Usuario>(updated, HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalError("Something went wrong :/");
        }
    }

}