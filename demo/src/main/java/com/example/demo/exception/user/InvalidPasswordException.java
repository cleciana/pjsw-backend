package com.example.demo.exception.user;

/**
 * InvalidPasswordException
 */
public class InvalidPasswordException extends RuntimeException {

    private static final long serialVersionUID = 3821513477929920178L;

    public InvalidPasswordException(String msg) {
        super(msg);
    }
}