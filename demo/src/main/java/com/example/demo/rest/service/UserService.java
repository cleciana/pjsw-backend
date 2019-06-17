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

    public UserService(UserDao<User, String> UserDao) {
        this.UserDao = UserDao;
    }

    public User create(User user) {
        return this.UserDao.save(user);
    }

    public User update(User userToUpdate) {

        User user = this.UserDao.findById(userToUpdate.getEmail());
        if (user == null) {
            throw new UserNotFoundException("Could not update. User does not exist.");
        }
        return this.UserDao.save(userToUpdate);
    }

    public void delete(String email) {
        this.UserDao.deleteById(email);
    }

    public User findById(String email) {
        return this.UserDao.findById(email);
    }
}