package com.example.demo.responses;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ComentarioResponse
 */
@Getter
@Entity
@AllArgsConstructor
public class ComentarioResponse {

    @Id
    private String username;
    private String content;
}