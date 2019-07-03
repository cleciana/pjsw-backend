package com.example.demo.exception.comment;

/**
 * CommentNotFoundException
 */
public class CommentNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CommentNotFoundException(String msg) {
        super(msg);
    }    
}