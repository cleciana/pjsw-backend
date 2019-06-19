package com.example.demo.rest.service;

import org.springframework.stereotype.Service;

import com.example.demo.exception.user.UserNotFoundException;
import com.example.demo.rest.dao.UserDao;
import com.example.demo.rest.model.User;

/**
 * UserService
 */
@Service
public class UserService {

    private final UserDao<User, String> UserDao;

    /**
     * Construtor
     * 
     * @param UserDao
     */
    public UserService(UserDao<User, String> UserDao) {
        this.UserDao = UserDao;
    }

    /**
     * Salva um novo usuário no banco de dados.
     * 
     * @param user Uma instancia de User criada quando um usuário se registra no
     *             sistema.
     * 
     * @return Retorna a Entidade 'User'
     */
    public User create(User user) {
        return this.UserDao.save(user);
    }

    /**
     * Atualiza informações do usuário no banco de dados.
     * 
     * @param userToUpdate Uma instancia de User, atualizado, que deve substituir um
     *                     usuário existente.
     * 
     * @return Retorna a Entidade User
     */
    public User update(User userToUpdate) {

        User user = this.UserDao.findById(userToUpdate.getEmail()).get();
        if (user == null) {
            throw new UserNotFoundException("Could not update. User does not exist.");
        }
        return this.UserDao.save(userToUpdate);
    }

    public void delete(String email) {
        this.UserDao.deleteById(email);
    }

    public User findById(String email) {
        return this.UserDao.findById(email).get();
    }
}