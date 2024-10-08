package com.example.demo.Analytics;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Service;

@Service
public class AnalyticsService {
    @KafkaListener(topics = "url_visited", groupId = "my_group")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }
}
