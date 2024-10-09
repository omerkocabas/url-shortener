package com.example.demo.Read;

import com.example.demo.Model;
import jakarta.annotation.PostConstruct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReadRepository extends JpaRepository<Model, Long> {
    Optional<Model> findByShortUrl(String string);
}
