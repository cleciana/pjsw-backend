package com.example.demo.rest.dao;

import java.util.Optional;

import com.example.demo.rest.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserDao
 */
@Repository
public interface UsuarioDAO<T, ID> extends JpaRepository<Usuario, String> {

    Usuario save(Usuario user);

    Optional<Usuario> findById(String email);
}