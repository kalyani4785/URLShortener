package com.example.urlShortener.service;

import com.example.urlShortener.entity.ShortUrl;
import com.example.urlShortener.exception.NotFoundException;
import com.example.urlShortener.repository.ShortUrlRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.Random;

@Service
public class ShortUrlService {
    private final ShortUrlRepository repo;
    private final Random random = new Random();
    private static final String BASE62= "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int DEFAULT_LENGTH=7;

    public ShortUrlService(ShortUrlRepository repo) {
        this.repo = repo;
    }

    public ShortUrl create(String originalUrl, String customCode, OffsetDateTime expiresAt){
        String code = customCode.isBlank() || customCode == null ? generateCode() : customCode;
        if(repo.existsByCode(code)){
            throw new IllegalArgumentException("Custom code already in use");
        }
        ShortUrl shortUrl = new ShortUrl(originalUrl,customCode, expiresAt);
        return repo.save(shortUrl);
    }

    public ShortUrl getByCode(String code){
        ShortUrl s = repo.findByCode(code).orElseThrow(()-> new NotFoundException("Short URL not found"));
        if(s.getExpiresAt() !=null && s.getExpiresAt().isBefore(OffsetDateTime.now())){
            throw new NotFoundException("Short URL has expired!");
        }
        return s;
    }

    @Transactional
    public int incrementHitCount(String code){
        int updated= repo.incrementHitCount(code);
        if(updated == 0){
            throw new NotFoundException("Short URL not found");
        }
        return updated;
    }

    public ShortUrl update(String code, String newUrl, String newCode, OffsetDateTime expiresAt){
        ShortUrl s= repo.findByCode(code).orElseThrow(()-> new NotFoundException("Not found"));
        if(newCode!=null && !newCode.equals(code)){
            if(repo.existsByCode(newCode)){
                throw  new NotFoundException("Custom code already exists");
            }
            s.setCode(newCode);
        }
        if(newUrl!=null) s.setOriginalUrl(newUrl);
        s.setExpiresAt(expiresAt);
        return repo.save(s);
    }

    public void delete(String code){
        ShortUrl s= repo.findByCode(code).orElseThrow(()-> new NotFoundException("Url not found"));
        repo.delete(s);
    }

    private String generateCode() {
        for(int attempt=0;attempt<10;attempt++){
            String code= randomBase62(DEFAULT_LENGTH);
            if(!repo.existsByCode(code)) return code;
        }
        String code;
        do{
            code= randomBase62(DEFAULT_LENGTH+1);
        } while (repo.existsByCode(code));

        return code;
    }

    private String randomBase62(int length){
        StringBuilder sb= new StringBuilder();
        for(int i=0;i<length;i++){
            sb.append(BASE62.charAt(random.nextInt(BASE62.length())));
        }
        return sb.toString();
    }
}
