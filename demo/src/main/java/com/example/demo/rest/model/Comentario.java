package com.example.demo.rest.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Comment
 */
@Data
@Entity
@NoArgsConstructor
public class Comentario {

    @Id
    private int id;
    private int disciplinaId;

    private boolean deleted;
    private String username;  
    private String content;

    public Comentario(String username, String content) {
        this.username = username;
        this.content = content;
        this.deleted = false;
    }
    
    public boolean isDeletd() {
        return this.deleted == true;
    }

	public void setDisciplinaId(int id2) {
        this.disciplinaId = id2;
	}
}