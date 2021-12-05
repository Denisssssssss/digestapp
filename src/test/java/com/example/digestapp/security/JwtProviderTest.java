package com.example.digestapp.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Date;

@SpringBootTest
public class JwtProviderTest {

    @Value("${security.jwt.secret}")
    private String JWT_SECRET;

    @Autowired
    private TokenProvider provider;

    @Test
    public void validateTokenTest() {
        Long userId = 1L;
        String token = provider.generate(userId);

        Assertions.assertFalse(provider.validate(null));
        Assertions.assertTrue(provider.validate(token));

        Instant expireDate = Instant.now().plusMillis(-1000);
        String badToken = Jwts.builder()
                .setSubject(userId.toString())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(expireDate))
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();

        Assertions.assertFalse(provider.validate(badToken));
    }
}