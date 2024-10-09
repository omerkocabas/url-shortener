package com.example.demo.Write;

import com.example.demo.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class WriteService {

    private final WriteRepository writeRepository;
    private static final String URL_REGEX =
            "^(http|https)://([\\w\\-]+\\.)+[\\w\\-]+(/[\\w\\- ./?%&=]*)?$";

    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    @Autowired
    public WriteService(WriteRepository writeRepository){
        this.writeRepository = writeRepository;
    }


    private String GenerateHash(Map<String, String> url){
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

    public ResponseEntity<String> insertUrl(@RequestBody Map<String, String> url){
        var longUrl = url.get("url");
        if(!isValidUrl(longUrl)){
            throw new RuntimeException();
        }

        String stored_hash = this.GenerateHash(url);
        var model = new Model(1L,stored_hash, longUrl);
        writeRepository.save(model);
        var returnString = "localhost:8080/"+ stored_hash;
        return new ResponseEntity<>(returnString, HttpStatus.CREATED);
    }

    private boolean isValidUrl(String input) {
        if (URL_PATTERN.matcher(input).matches()) {
            try {
                new URI(input);
                return true;
            } catch (URISyntaxException e) {
                return false;
            }
        }
        return false;
    }
}
