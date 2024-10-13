package com.example.demo.Write;

import com.example.demo.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WriteRepository extends JpaRepository<Model, Long> {
    Optional<Model> findByLongUrl(String string);
}