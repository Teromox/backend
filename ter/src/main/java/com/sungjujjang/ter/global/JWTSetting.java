package com.sungjujjang.ter.global;

import com.sungjujjang.ter.global.error.exception.NotValidJwtErr;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTSetting {
    @Value("${jwt.secret}")
    private String secretKeyString;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(secretKeyString.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(String userId, Long expirationTime) {
        Date now = new Date();
        Date expDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .subject(userId)
                .issuedAt(now)
                .expiration(expDate)
                .signWith(secretKey)
                .compact();
    }

    public String checkToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (JwtException e) {
            throw NotValidJwtErr.EXCEPTION;
        }
    }
}