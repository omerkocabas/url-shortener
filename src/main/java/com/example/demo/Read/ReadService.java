package com.example.demo.Read;

import com.example.demo.Exception.UrlNotFoundException;
import com.example.demo.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReadService {
    private static final String TOPIC = "url_visited";

    private final ReadRepository readRepository;

    @Autowired
    public ReadService(ReadRepository readRepository) {
        this.readRepository = readRepository;
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
    }

    public ResponseEntity<Model> getUrl(String shortUrl) {
        Model model = readRepository.findByShortUrl(shortUrl)
                .orElseThrow(()->new UrlNotFoundException(shortUrl));
        this.sendMessage(model.getLongUrl() );
        return new ResponseEntity<>(model, HttpStatus.OK);
    }


}
