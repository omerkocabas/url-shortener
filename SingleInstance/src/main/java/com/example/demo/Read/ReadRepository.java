package com.example.demo.Read;
import com.example.demo.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ReadRepository extends JpaRepository<Model, Long> {
    Optional<Model> findByShortUrl(String string);
}
