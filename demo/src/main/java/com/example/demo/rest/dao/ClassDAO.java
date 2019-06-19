package com.example.demo.rest.dao;

import java.util.List;

import com.example.demo.rest.model.Class;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ClassDAO
 */
@Repository
public interface ClassDAO<T, ID> extends JpaRepository<Class, Integer> {

    Class save(Class aClass);

    Class findById(int id);

    
}