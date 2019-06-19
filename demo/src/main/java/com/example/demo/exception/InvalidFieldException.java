package com.example.demo.exception;

/**
 * InvalidPasswordException
 */
public class InvalidFieldException extends RuntimeException {

    private static final long serialVersionUID = 3821513477929920178L;

    public InvalidFieldException(String msg) {
        super(msg);
    }
}