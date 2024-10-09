package com.example.demo;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.RestController;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;

@Entity
public class Model {

    public Model(Long id, String shortUrl, String longUrl){
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
    }

    public Model(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String shortUrl;
    @Column
    private String longUrl;

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
}