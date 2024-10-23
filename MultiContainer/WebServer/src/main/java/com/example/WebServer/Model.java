package com.example.WebServer;

import java.time.LocalDateTime;

public class Model {

    public Model(Long id, String shortUrl, String longUrl, LocalDateTime createdAt){
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
    }

    public Model(){

    }
    private Long id;
    private String shortUrl;
    private String longUrl;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}