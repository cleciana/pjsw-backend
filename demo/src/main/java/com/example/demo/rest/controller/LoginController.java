package com.example.demo.rest.controller;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.example.demo.exception.InvalidFieldException;
import com.example.demo.exception.user.UserNotFoundException;
import com.example.demo.rest.model.Usuario;
import com.example.demo.rest.service.UsuarioService;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
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
@RequestMapping({ "v1/auth" })
public class LoginController {

    // EXPIRATION_TIME = 10 dias
    static final long EXPIRATION_TIME = 860_000_000;
    private final String TOKEN_KEY = "banana";

    @Autowired
    private UsuarioService userService;

    @PostMapping("/login")
    public LoginResponse authenticate(@RequestBody Usuario user) {
        Optional<Usuario> authUser = userService.findById(user.getEmail());

        // Verifica se o usuário está cadastrado.
        if(!authUser.isPresent()) {
            throw new UserNotFoundException("User not found.");
        }

        Usuario usuario = authUser.get();

        // Checa se a senha recebida eh igual a cadastrada.
        if (!usuario.getPassword().equals(user.getPassword())) {
            throw new  InvalidFieldException("Password invalid or incorrect. Try again.");
        }

        // Por último, gera o token e retorna ao usuario.
        String token = Jwts.builder()
        .setSubject(usuario.getEmail())
        .signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .compact();

        return new LoginResponse(token);
    }

    public String getTokenEmail(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        String name = null;
        if (token != null) {
            name = Jwts.parser()
            .setSigningKey(TOKEN_KEY)
            .parseClaimsJws("Bearer")
            .getBody()
            .getSubject();
        }
        return name;
    }

    private class LoginResponse {

        @Getter
        public String token;

        public LoginResponse(String token) {
            this.token = token;
        }
    
        
    }
}

