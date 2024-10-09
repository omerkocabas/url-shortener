package com.example.demo.Write;

import com.example.demo.Model;
import com.example.demo.Read.ReadController;
import com.example.demo.Read.ReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigInteger;
import java.security.*;
import java.util.Map;

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
