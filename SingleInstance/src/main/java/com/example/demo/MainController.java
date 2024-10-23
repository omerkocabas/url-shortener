package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Controller
public class MainController {

    private final RestTemplate restController;

    @Autowired
    public MainController(RestTemplate restController){
        this.restController = restController;
    }

    @GetMapping("/{shortUrl}")
    public String getUrl(@PathVariable String shortUrl){

        Model item = restController.getForObject("http://localhost:8080/api/v1/" + shortUrl, Model.class);
        if(item == null){
            return "Not found";
        }
       return "redirect:" +  item.getLongUrl();
    }

    @GetMapping("/")
    public String showHomePage(){
        return "index";
    }
}
