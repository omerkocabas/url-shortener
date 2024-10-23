package com.example.Read.Service;

import com.example.Read.Constants.Constants;
import com.example.Read.Exceptions.UrlNotFoundException;
import com.example.Read.Model;
import com.example.Read.Repository.ReadRepository;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReadService {

    private final ReadRepository readRepository;

    @Autowired
    public ReadService(ReadRepository readRepository) {
        this.readRepository = readRepository;
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        kafkaTemplate.send(Constants.kafkaTopic, message);
    }
    public ResponseEntity<Model> getUrl(String shortUrl) {
        Model model = readRepository.findByShortUrl(shortUrl)
                .orElseThrow(()->new UrlNotFoundException(Constants.urlNotFound+shortUrl));
        this.sendMessage(model.getLongUrl() );
        return new ResponseEntity<>(model, HttpStatus.OK);
    }


}
