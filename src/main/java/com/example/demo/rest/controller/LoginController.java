package com.example.demo.rest.controller;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.example.demo.exception.InvalidFieldException;
import com.example.demo.exception.user.UserNotFoundException;
import com.example.demo.rest.model.Usuario;
import com.example.demo.rest.service.UsuarioService;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;

//@CrossOrigin(origins = "*")
@RestController()
@RequestMapping({ "v1/auth" })
@Api(value = "Login", description = "Recebe a requisicao de login, processa e retorna ao usuario um token de autenticacao que eh usado em interacoes futuras com esta API.")
public class LoginController {

    // EXPIRATION_TIME = 10 dias
    static final long EXPIRATION_TIME = 860_000_000;
    private final String TOKEN_KEY = "banana";
    private final String TOKEN_PREFIX = "Bearer";

    @Autowired
    private UsuarioService userService;

    //@CrossOrigin
    @ApiOperation(value = "Recebe os dados do usuario e retorna um token de autenticacao.")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody Usuario user) {
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

        ResponseEntity<LoginResponse> res = new ResponseEntity<LoginResponse>(new LoginResponse(token) , HttpStatus.OK);
        return res ;
    }

    /**
     * Decodifica o token e recuperar o identificador do usuario.
     */
    public String getTokenEmail(String token) {

        String name = "";
        if (token != null) {
            name = Jwts.parser()
            .setSigningKey(TOKEN_KEY)
            .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
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

