package com.example.urlShortener.entity;


import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name="short_urls",
indexes = {@Index(name="idx_code",columnList="code",unique = true)})

public class ShortUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 2048)
    private String originalUrl;

    @Column(nullable = false,unique = true,length = 100)
    private String code;

    @Column(nullable = false)
    private Long hitCount=0L;

    private OffsetDateTime lastAccessedAt;
    private OffsetDateTime expiresAt;

    public ShortUrl(){}

    public ShortUrl(String originalUrl, String code, OffsetDateTime expiresAt){
        this.originalUrl = originalUrl;
        this.code = code;
        this.expiresAt = expiresAt;
    }


//    Getters and Setters
    public Long getId() {
        return id;
    }
    public String getOriginalUrl() {
        return originalUrl;
    }
    public String getCode() {
        return code;
    }
    public Long getHitCount() {
        return hitCount;
    }
    public OffsetDateTime getLastAccessedAt() {
        return lastAccessedAt;
    }
    public OffsetDateTime getExpiresAt() {
        return expiresAt;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public void setHitCount(Long hitCount) {
        this.hitCount = hitCount;
    }
    public void setLastAccessedAt(OffsetDateTime lastAccessedAt) {
        this.lastAccessedAt = lastAccessedAt;
    }
    public void setExpiresAt(OffsetDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

}
