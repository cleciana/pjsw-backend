package com.example.demo.rest.controller;

import java.util.Date;

import com.example.demo.exception.InvalidFieldException;
import com.example.demo.exception.user.UserNotFoundException;
import com.example.demo.rest.model.Usuario;
import com.example.demo.rest.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;

/**
 * LoginController
 */
@RestController()
@RequestMapping({"v1/auth"})
public class LoginController {

    private final String TOKEN_KEY = "applepen";

    @Autowired
    private UsuarioService userService;

    @PostMapping("/login")
    public LoginResponse authenticate(@RequestBody Usuario user) {
        Usuario authUser = userService.findById(user.getEmail());

        // Verifica se o usuário está cadastrado.
        if(authUser == null) {
            throw new UserNotFoundException("User not found.");
        }

        // Verifica se o campo login eh igual esta correto.
        if(!authUser.getLogin().equals(user.getLogin())) {
            throw new InvalidFieldException("Login invalid or incorrect. Try again.");
        }

        // Checa se a senha recebida eh igual a cadastrada.
        if (!authUser.getPassword().equals(user.getPassword())) {
            throw new  InvalidFieldException("Password invalid or incorrect. Try again.");
        }

        // Por último, gera o token e retorna ao usuario.
        String token = Jwts.builder()
        .setSubject(authUser.getEmail())
        .signWith(SignatureAlgorithm.ES512, TOKEN_KEY)
        .setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
        .compact();

        return new LoginResponse(token);
    }

    private class LoginResponse {

        @Getter
        public String token;

        public LoginResponse(String token) {
            this.token = token;
        }
    
        
    }
}

