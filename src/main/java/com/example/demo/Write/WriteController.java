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

    private final ReadRepository readRepository;

    @Autowired
    public WriteController(ReadRepository readRepository){
        this.readRepository = readRepository;

    }

    @PostMapping("/api/v1")
    public String insertUrl(@RequestBody Map<String, String> url){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        var longUrl = url.get("url");
        md.update(longUrl.getBytes());
        byte[] digest = md.digest();
        String hash = String.format("%032x", new BigInteger(1, digest));
        String stored_hash = hash.substring(0,7);
        var model = new Model(1L,stored_hash, longUrl);
        readRepository.save(model);
        return "localhost:8080/"+ stored_hash;
    }
}
