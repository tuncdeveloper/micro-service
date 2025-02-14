package com.ferhat.microservice.auth_service.service;


import com.ferhat.microservice.auth_service.model.AuthenticationToken;
import com.ferhat.microservice.auth_service.repository.AuthenticationTokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationTokenService {

    @Autowired
    private  AuthenticationTokenRepository tokenRepository;

    private final String SECRET_KEY = "your-secret-key-here-1234567890-1234567890-1234567890";

    public String generateToken(UUID customerId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiration = now.plusHours(1); // Token expires in 1 hour

        String token = Jwts.builder()
                .setSubject(customerId.toString())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Timestamp.valueOf(expiration))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();

        AuthenticationToken authenticationToken = new AuthenticationToken();
        authenticationToken.setCustomerId(customerId);
        authenticationToken.setToken(token);
        authenticationToken.setExpiresAt(expiration);
        authenticationToken.setCreatedAt(now);

        tokenRepository.save(authenticationToken);

        return token;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public UUID getCustomerIdFromToken(String token) {
        return UUID.fromString(Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
    }
}
