package main.java.com.example.demo.rest;

import java.util.Comparator;

import main.java.com.example.demo.rest.dto.ContComentariosDTO;

/**
 * ComentarioComparator
 */
public class ComentarioComparator implements Comparator<ContComentariosDTO> {

    @Override
    public int compare(ContComentariosDTO c1, ContComentariosDTO c2) {

        return c1.compareTo(c2);
    }
}