package com.example.demo.Analytics;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.kv.GetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Service;

@Service
public class AnalyticsService {
    @KafkaListener(topics = "url_visited", groupId = "my_group")
    public void consume(String message) {
        this.incrementVisit(message);
    }

    private final Collection collection;

    @Autowired
    public AnalyticsService(Bucket bucket) {
        this.collection = bucket.defaultCollection();
    }

    public void incrementVisit(String url) {
        try {
            GetResult getResult = collection.get(url);
            Integer currentVisits = getResult.contentAs(Integer.class);
            collection.upsert(url, currentVisits + 1);
        } catch (Exception e) {
            collection.upsert(url, 1);
        }
    }

    public Integer getVisitCount(String url) {
        try {
            GetResult getResult = collection.get(url);
            return getResult.contentAs(Integer.class);
        } catch (Exception e) {
            return 0;
        }
    }

}
