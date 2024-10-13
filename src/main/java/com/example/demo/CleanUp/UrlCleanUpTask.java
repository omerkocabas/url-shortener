package com.example.demo.CleanUp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class UrlCleanUpTask {
    private final CleanupRepository cleanupRepository;
    private static final int TTL_DAYS = 1;

    @Scheduled(cron = "0 0 0 * * ?")
    public void cleanUpOldUrls() {
        LocalDateTime expirationTime = LocalDateTime.now().minusDays(TTL_DAYS);
        cleanupRepository.deleteByCreatedAtBefore(expirationTime);
        System.out.println("Cleaned up URLs older than " + TTL_DAYS + " days.");
    }

    @Autowired
    UrlCleanUpTask(CleanupRepository cleanupRepository){
        this.cleanupRepository = cleanupRepository;
    }

}

