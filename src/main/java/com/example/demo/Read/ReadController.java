package com.example.demo.Read;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Model;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ReadController {
    public final ReadRepository readRepository;

    public ReadController(ReadRepository readRepository){
        this.readRepository = readRepository;
    }
    @GetMapping("/{short_url}")
    public String getUrl(@PathVariable String short_url){
        var urlList = readRepository.findByShortUrl(short_url).stream().toList();
        var longUrl = urlList.getFirst().getLongUrl();
        return "redirect:" +  longUrl;
    }
}
