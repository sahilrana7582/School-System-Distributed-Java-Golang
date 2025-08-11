package com.example.user_service.user_service.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {


    private final String secretKey;
    private final Duration expireTime = Duration.ofMinutes(60);

    public JwtService(
            @Value("${spring.jwt.secret.key}") String secretKey
    ){
        this.secretKey = secretKey;
    }


    public String generateToken(Claim claim){
        return Jwts.builder()
                .setSubject(claim.name())
                .addClaims(Map.of(
                        "tenantId", claim.tenantId(),
                        "userId", claim.userId(),
                        "schoolId", claim.schoolId()
                ))
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(expireTime)))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claim extractClaim(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody();

        return new Claim(
                claims.getSubject(),
                claims.get("tenantId", String.class),
                claims.get("userId", String.class),
                claims.get("schoolId", String.class)
        );
    }


    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            boolean notExpired = claims.getExpiration().after(new java.util.Date());

            return notExpired;
        }
        catch (ExpiredJwtException e) {
            System.out.println("❌ Token expired at: " + e.getClaims().getExpiration());
        }
        catch (Exception e) {
            System.out.println("❌ Invalid token: " + e.getMessage());
        }
        return false;
    }


}
