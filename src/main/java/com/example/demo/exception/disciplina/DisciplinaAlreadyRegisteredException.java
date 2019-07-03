package com.example.demo.exception.disciplina;

/**
 * DisciplinaAlreadyRegisteredException
 */
public class DisciplinaAlreadyRegisteredException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DisciplinaAlreadyRegisteredException(String msg) {
        super(msg);
    }    
}