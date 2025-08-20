package com.example.urlShortener.repository;


import com.example.urlShortener.entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
    Optional<ShortUrl> findByCode(String code);
    boolean existsByCode(String code);


    /*
     * Increments the click count for a given short URL code and updates the last accessed timestamp.
     *@Param("code") binds the method parameter code to the :code placeholder in the query.
     * @Modifying tells Spring Data JPA that this query will change data (not just read).
     * JPQL (Java Persistence Query Language) is used to define the query.
     * @return the number of rows affected
     */
    @Modifying
    @Query("UPDATE ShortUrl s SET s.hitCount = s.hitCount + 1, s.lastAccessedAt = CURRENT_TIMESTAMP WHERE s.code = :code")
    int incrementHitCount(@Param("code") String code);
}
