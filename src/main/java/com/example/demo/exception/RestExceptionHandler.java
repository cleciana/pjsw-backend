package com.example.demo.exception;

import java.util.Date;

import com.example.demo.exception.comment.CommentNotFoundException;
import com.example.demo.exception.disciplina.ClassNotRegisteredException;
import com.example.demo.exception.user.UserNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * RestExceptionHandler
 */
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomRestError> handleAnyException(Exception ex, WebRequest request) {
       CustomRestError errorMessage = new CustomRestError(new Date(), ex.getMessage(), request.getDescription(false));
       return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidFieldException.class)
    public ResponseEntity<CustomRestError> invalidField(Exception exception, WebRequest request) {
        CustomRestError error = new CustomRestError(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<CustomRestError> userNotFound(Exception exception, WebRequest request) {
        CustomRestError error = new CustomRestError(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClassNotRegisteredException.class)
    public ResponseEntity<CustomRestError> classNotFound(Exception exception, WebRequest request) {
        CustomRestError error = new CustomRestError(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<CustomRestError> notAuthorized(Exception exception, WebRequest request) {
        CustomRestError error = new CustomRestError(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<CustomRestError> commentNotFound(Exception exception, WebRequest request) {
        CustomRestError error = new CustomRestError(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}