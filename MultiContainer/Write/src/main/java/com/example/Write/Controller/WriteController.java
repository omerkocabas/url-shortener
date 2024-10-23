package com.example.Write.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Map;
import com.example.Write.Service.WriteService;

@RestController
public class WriteController {

    private final WriteService writeService;

    @Autowired
    public WriteController(WriteService writeService){
        this.writeService = writeService;
    }

    @PostMapping("/api/v1/shorten")
    public ResponseEntity<String> insertUrl(@RequestBody Map<String, String> url){
        return writeService.insertUrl(url);
    }
}