package com.example.demo.Read;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Model;

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
