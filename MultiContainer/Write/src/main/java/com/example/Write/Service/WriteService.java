package com.example.Write.Service;
import com.example.Write.Exceptions.UrlNotValidException;
import com.example.Write.Model;
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
import java.time.LocalDateTime;
import java.util.Map;
import java.util.regex.Pattern;
import com.example.Write.Repository.WriteRepository;
import com.example.Write.Constants.Constants;

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
            throw new UrlNotValidException(Constants.urlNotValid + longUrl);
        }
        var readModel = writeRepository.findByLongUrl(longUrl);
        if(readModel.isEmpty()){
            String stored_hash = this.GenerateHash(url);
            var model = new Model(1L,stored_hash, longUrl, LocalDateTime.now());
            writeRepository.save(model);
            var returnString = Constants.baseUrl + stored_hash;
            return new ResponseEntity<>(returnString, HttpStatus.CREATED);
        }
        else{
            var shortUrl = readModel.get().getShortUrl();
            var returnString = Constants.baseUrl + shortUrl;
            return new ResponseEntity<>(returnString, HttpStatus.OK);
        }

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