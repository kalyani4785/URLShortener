package com.example.urlShortener.controller;

import com.example.urlShortener.dto.CreateShortUrlRequest;
import com.example.urlShortener.dto.UpdateShortUrlRequest;
import com.example.urlShortener.entity.ShortUrl;
import com.example.urlShortener.service.ShortUrlService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("api/urls")
public class UrlController {

    private final ShortUrlService shortUrlService;
    public UrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateShortUrlRequest req) {
        ShortUrl created = shortUrlService.create(req.getOriginalUrl(), req.getCode(), req.getExpiresAt());
        System.out.println("Created ShortURL: " + created.getCode() + "\n" +
                "Original URL: " + created.getOriginalUrl() + "\n" +
                "Expires At: " + created.getExpiresAt());

        // Construct the short URL
        String shortUrl = "http://localhost:8080/" + created.getCode();

        return ResponseEntity.created(URI.create("/" + created.getCode()))
                .body(Map.of(
                        "id", created.getId(),
                        "code", created.getCode(),
                        "shortUrl", shortUrl,
                        "originalUrl", created.getOriginalUrl()
                ));
    }
//    @PostMapping
//    public ResponseEntity<?> create(@Valid @RequestBody CreateShortUrlRequest req){
//        ShortUrl created = shortUrlService.create(req.getOriginalUrl(),req.getCode(),req.getExpiresAt());
//        System.out.println("Created ShortURL: " + created.getCode() + "\n" +
//                "Original URL: " + created.getOriginalUrl() + "\n" +
//                "Expires At: " + created.getExpiresAt());
//        return ResponseEntity.created(URI.create("/" +created.getCode()))
//                .body(Map.of(
//                        "id", created.getId(),
//                        "code", created.getCode(),
//                        "shortUrl", created.getCode(),
//                        "originalUrl", created.getOriginalUrl()
//                ));
//    }

    @GetMapping("/{code}")
    public ResponseEntity<?> get(@PathVariable String code, @Valid @RequestBody UpdateShortUrlRequest req){
        ShortUrl updated = shortUrlService.update(code, req.getOriginalUrl(), req.getCustomCode(), req.getExpiresAt());
        return ResponseEntity.ok(Map.of(
                "id", updated.getId(),
                "code", updated.getCode(),
                "originalUrl", updated.getOriginalUrl()
        ));
    }

    @PutMapping("/{code}")
    public ResponseEntity<?> update(@PathVariable String code, @Valid @RequestBody UpdateShortUrlRequest req){
        ShortUrl updated = shortUrlService.update(code, req.getOriginalUrl(), req.getCustomCode(), req.getExpiresAt());
        return ResponseEntity.ok(Map.of(
           "id", updated.getId(),
           "code", updated.getCode(),
           "originalUrl", updated.getOriginalUrl()
        ));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<?> delete(@PathVariable String code) {
        shortUrlService.delete(code);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{code}/stats")
    public ResponseEntity<?> stats(@PathVariable String code) {
        ShortUrl shortUrl = shortUrlService.getByCode(code);
        if (shortUrl == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Map.of(
                "id", shortUrl.getId(),
                "code", shortUrl.getCode(),
                "originalUrl", shortUrl.getOriginalUrl(),
                "createdAt", shortUrl.getLastAccessedAt(),
                "expiresAt", shortUrl.getExpiresAt(),
                "clickCount", shortUrl.getHitCount()
        ));
    }
}
