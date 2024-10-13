package com.example.demo.Exception;

public class UrlNotValidException extends RuntimeException{
    private final String url;

    public UrlNotValidException(String url){
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
