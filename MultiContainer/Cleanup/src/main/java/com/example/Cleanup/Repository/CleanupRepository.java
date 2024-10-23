package com.example.Cleanup.Repository;

import com.example.Cleanup.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface CleanupRepository extends JpaRepository<Model, Long> {
    void deleteByCreatedAtBefore(LocalDateTime expirationTime);
}
