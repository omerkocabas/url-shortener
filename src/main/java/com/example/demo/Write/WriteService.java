package com.example.demo.Write;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class WriteService {
    public String GenerateHash(Map<String, String> url){
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
        return hash.substring(0,7);
    }
}
