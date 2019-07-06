package com.example.demo.rest.dto;

/**
 * ComentarioResponse
 */
public class ComentarioDTO {

	private boolean deleted;
    private String username;
	private String content;
	private int id;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


}