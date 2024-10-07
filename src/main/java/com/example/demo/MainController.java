package com.example.demo;

import com.example.demo.Read.ReadController;
import com.example.demo.Write.WriteController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
