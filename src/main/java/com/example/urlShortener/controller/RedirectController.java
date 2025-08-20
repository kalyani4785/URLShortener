package com.example.urlShortener.controller;

import com.example.urlShortener.entity.ShortUrl;
import com.example.urlShortener.service.ShortUrlService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {
    private final ShortUrlService service;

    public RedirectController(ShortUrlService service){
        this.service = service;
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> redirect(@PathVariable String code) {
        ShortUrl url= service.getByCode(code);
        service.incrementHitCount(code);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(java.net.URI.create(url.getOriginalUrl()));
        return ResponseEntity.status(302).headers(headers).build();
    }
}
