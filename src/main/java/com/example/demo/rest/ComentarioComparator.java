package main.java.com.example.demo.rest;

import java.util.Comparator;

import com.example.demo.rest.dto.ContComentariosDTO;

/**
 * ComentarioComparator
 */
public class ComentarioComparator<ContComentariosDT> implements Comparator<ContComentariosDTO> {

    public ComentarioComparator() {
        super();
    }

    @Override
    public int compare(ContComentariosDTO c1, ContComentariosDTO c2) {
        return c1.compareTo(c2);
    }
}