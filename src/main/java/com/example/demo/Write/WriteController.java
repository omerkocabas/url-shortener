package com.example.demo.Write;

import com.example.demo.Model;
import com.example.demo.Read.ReadController;
import com.example.demo.Read.ReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigInteger;
import java.security.*;
import java.util.Map;

@RestController
public class WriteController {

    private final WriteRepository writeRepository;
    private final WriteService writeService;

    @Autowired
    public WriteController(WriteRepository writeRepository, WriteService writeService){
        this.writeRepository = writeRepository;
        this.writeService = writeService;

    }

    @PostMapping("/api/v1")
    public String insertUrl(@RequestBody Map<String, String> url){
        String stored_hash = writeService.GenerateHash(url);
        var longUrl = url.get("url");
        var model = new Model(1L,stored_hash, longUrl);
        writeRepository.save(model);
        return "localhost:8080/"+ stored_hash;
    }
}
