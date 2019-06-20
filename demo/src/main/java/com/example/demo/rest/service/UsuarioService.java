package com.example.demo.rest.service;

import org.springframework.stereotype.Service;

import com.example.demo.exception.user.UserNotFoundException;
import com.example.demo.rest.dao.UsuarioDAO;
import com.example.demo.rest.model.Usuario;

/**
 * UserService
 */
@Service
public class UsuarioService {

    private final UsuarioDAO<Usuario, String> uDao;

    /**
     * Construtor
     * 
     * @param UserDao
     */
    public UsuarioService(UsuarioDAO<Usuario, String> UserDao) {
        this.uDao = UserDao;
    }

    /**
     * Salva um novo usuário no banco de dados.
     * 
     * @param user Uma instancia de User criada quando um usuário se registra no
     *             sistema.
     * 
     * @return Retorna a Entidade 'User'
     */
    public Usuario create(Usuario user) {
        return this.uDao.save(user);
    }

    /**
     * Atualiza informações do usuário no banco de dados.
     * 
     * @param userToUpdate Uma instancia de User, atualizado, que deve substituir um
     *                     usuário existente.
     * 
     * @return Retorna a Entidade User
     */
    public Usuario update(Usuario userToUpdate) {

        Usuario user = this.uDao.findById(userToUpdate.getEmail()).get();
        if (user == null) {
            throw new UserNotFoundException("Could not update. User does not exist.");
        }
        return this.uDao.save(userToUpdate);
    }

    public void delete(String email) {
        this.uDao.deleteById(email);
    }

    public Usuario findById(String email) {
        return this.uDao.findById(email).get();
    }
}