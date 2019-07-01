package com.example.demo.responses;

/**
 * ComentarioResponse
 */
public class ComentarioResponse {

    private String username;
    private String content;

    public ComentarioResponse() {
    }

    public ComentarioResponse(String username, String content) {
        this.username = username;
        this.content = content;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String u) {
        this.username = u;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String u) {
        this.content = u;
    }
}