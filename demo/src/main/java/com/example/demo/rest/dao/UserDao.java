package com.example.demo.rest.dao;

import java.util.Optional;

import com.example.demo.rest.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserDao
 */
@Repository
public interface UserDao<T, ID> extends JpaRepository<User, String> {

    User save(User user);

    Optional<User> findById(String email);
}