package com.example.demo.CleanUp;

import com.example.demo.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface CleanupRepository extends JpaRepository<Model, Long> {
    void deleteByCreatedAtBefore(LocalDateTime expirationTime);
}
