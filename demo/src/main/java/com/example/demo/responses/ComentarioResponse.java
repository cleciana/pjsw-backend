package com.example.demo.responses;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ComentarioResponse
 */
@Getter
@Entity
@AllArgsConstructor
public class ComentarioResponse {

    private String username;
    private String content;
}