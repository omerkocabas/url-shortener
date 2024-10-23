package com.example.Cleanup.Service;

import com.example.Cleanup.Repository.CleanupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class CleanupService {
    private final CleanupRepository cleanupRepository;
    private static final int TTL_DAYS = 1;

    @Scheduled(cron = "0 0 0 * * ?")
    public void cleanUpOldUrls() {
        LocalDateTime expirationTime = LocalDateTime.now().minusDays(TTL_DAYS);
        cleanupRepository.deleteByCreatedAtBefore(expirationTime);
    }

    @Autowired
    CleanupService(CleanupRepository cleanupRepository){
        this.cleanupRepository = cleanupRepository;
    }

}