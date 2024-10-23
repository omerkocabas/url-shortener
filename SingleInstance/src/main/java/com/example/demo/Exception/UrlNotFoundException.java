package com.example.demo.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UrlNotFoundException extends RuntimeException{
    private final String shortUrl;

    public UrlNotFoundException(String shortUrl){
        this.shortUrl = shortUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

}
