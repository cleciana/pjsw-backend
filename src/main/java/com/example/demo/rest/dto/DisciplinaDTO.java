package com.example.demo.rest.dto;

/**
 * DisciplinaDTO
 */

public class DisciplinaDTO {

    private boolean deuLike;
    private int qtdLikes;
    private String description;
    private int id;


	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQtdLikes() {
		return this.qtdLikes;
	}

	public void setQtdLikes(int qtdLikes) {
		this.qtdLikes = qtdLikes;
    }
    
	public boolean getDeuLike() {
		return this.deuLike;
    }
    
	public void setDeuLike(boolean deuLike) {
		this.deuLike = deuLike;
	}
    
}