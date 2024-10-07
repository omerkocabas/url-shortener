package com.example.demo.Read;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Model;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ReadController {
    public final ReadRepository readRepository;

    public ReadController(ReadRepository readRepository){
        this.readRepository = readRepository;
    }
    @GetMapping("/api/v1/{short_url}")
    public Model getUrl(@PathVariable String short_url){
        var modelList =  readRepository.findByShortUrl(short_url).stream().collect(Collectors.toList());
        try{
            return modelList.getFirst();
        } catch (Exception e) {
            return null;
        }

    }
}
