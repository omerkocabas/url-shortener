package com.example.demo.Read;
import com.example.demo.Exception.UrlNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ReadController {
    private final ReadService readService;

    @Autowired
    public ReadController(ReadService readService){
        this.readService = readService;
    }

    @GetMapping("/api/v1/{short_url}")
    public ResponseEntity<Model> getUrl(@PathVariable String short_url){
        return  readService.getUrl(short_url);
    }
}
