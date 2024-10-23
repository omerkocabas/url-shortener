package com.example.WebServer.Controller;

import com.example.WebServer.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class MainController {

    private final RestTemplate restTemplate;

    @Autowired
    public MainController(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{shortUrl}")
    public String getUrl(@PathVariable String shortUrl){

        Model item = restTemplate.getForObject("http://read:8080/api/v1/" + shortUrl, Model.class);
        if(item == null){
            return "Not found";
        }
        return "redirect:" +  item.getLongUrl();
    }

    @PostMapping("/shorten")
    @ResponseBody
    public String shortenUrl(@RequestBody String longUrl){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        String requestBody = "{ \"url\": \"" + longUrl + "\" }";

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        var requestUrl = "http://write:8080/api/v1/shorten";

        ResponseEntity<String> response = restTemplate.exchange(
                requestUrl, HttpMethod.POST, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK || response.getStatusCode() == HttpStatus.CREATED) {
            return response.getBody();
        } else {
            return "Invalid URL";
        }

    }

    @GetMapping("/")
    public String showHomePage(){
        return "index";
    }
}