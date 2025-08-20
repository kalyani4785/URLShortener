package com.example.urlShortener.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.OffsetDateTime;

public class UpdateShortUrlRequest {
    //    Getters and Setters
    @Getter
    private String originalUrl;

    @Size(max = 100)
    private String code;

    private OffsetDateTime expiresAt;

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
    public String getCustomCode() {
        return code;
    }
    public void setCustomCode(String customCode) {
        this.code = customCode;
    }
    public OffsetDateTime getExpiresAt() {
        return expiresAt;
    }
    public void setExpiresAt(OffsetDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
}
