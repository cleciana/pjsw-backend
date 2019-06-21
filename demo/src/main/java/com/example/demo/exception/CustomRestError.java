package com.example.demo.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * CustomRestError
 */
@Data
@AllArgsConstructor
public class CustomRestError {

    private Date date;
    private String message;
    private String description;

}