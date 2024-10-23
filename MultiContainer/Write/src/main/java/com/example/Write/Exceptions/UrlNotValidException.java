package com.example.Write.Exceptions;

public class UrlNotValidException extends RuntimeException{
    private final String url;

    public UrlNotValidException(String url){
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}