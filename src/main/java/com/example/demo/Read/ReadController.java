package com.example.demo.Read;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Model;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ReadController {
    private final ReadRepository readRepository;
    private KafkaTemplate<String, String> kafkaTemplate;
    private final ReadService readService;

    @Autowired
    public ReadController(ReadRepository readRepository, ReadService readService){
        this.readRepository = readRepository;
        this.readService = readService;
    }
    @GetMapping("/api/v1/{short_url}")
    public Model getUrl(@PathVariable String short_url){
        var modelList =  readRepository.findByShortUrl(short_url).stream().collect(Collectors.toList());

        if(!modelList.isEmpty()){
            readService.sendMessage(short_url);
            return modelList.getFirst();
        }
        return null;
    }
}
