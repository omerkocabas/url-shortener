package com.example.Analytics.Service;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.kv.GetResult;
import com.example.Analytics.Repository.AnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Service;

@Service
public class AnalyticsService {
    @KafkaListener(topics = "url_visited", groupId = "my_group")
    public void consume(String message) {
        analyticsRepository.incrementVisit(message);
    }

    private final AnalyticsRepository analyticsRepository;

    @Autowired
    public AnalyticsService(AnalyticsRepository analyticsRepository) {
        this.analyticsRepository = analyticsRepository;
    }

}
