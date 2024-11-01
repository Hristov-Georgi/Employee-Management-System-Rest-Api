package com.sirmaacademy.employeemanagementsystemrestapi.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    private static final Long THIRTY_SECONDS_IN_MILLIS = 30000L;

    @Value("${jwt.security.secret-key}")
    private String secret;

    @Value("${jwt.security.duration-ms}")
    private Long durationMs;

    public String createToken(String username) {
        return buildToken(username);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isJwtTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && isTokenNonExpired(token);
    }

    private boolean isTokenNonExpired(String token) {
        return new Date(System.currentTimeMillis()).before(extractClaim(token, Claims::getExpiration));
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private String buildToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis() - THIRTY_SECONDS_IN_MILLIS))
                .expiration(new Date(System.currentTimeMillis() + this.durationMs))
                .signWith(secretKey())
                .compact();
    }

    private SecretKey secretKey() {
        byte[] key = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(key);
    }


}
