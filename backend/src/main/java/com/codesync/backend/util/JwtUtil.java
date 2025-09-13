package com.codesync.backend.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtUtil {

    @Value("${JWT_SECRET}")
    private String jwtSecret;

    @Value("${JWT_EXPIRATION}")
    private Long jwtExp;

    private SecretKey getSigingKey(){
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(Long userId,String email){
        return Jwts.builder()
                .setSubject(userId.toString())
                .claim("email", email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExp))
                .signWith(getSigingKey())
                .compact();
    }
    public Long extractUserId(String token){
        String subject = Jwts.parserBuilder()
                        .setSigningKey(getSigingKey())
                        .build()
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject();
        return Long.parseLong(subject);
    }
    public String extractEmail(String token){
        return Jwts.parserBuilder()
                        .setSigningKey(getSigingKey())
                        .build()
                        .parseClaimsJws(token)
                        .getBody()
                        .get("email",String.class); 
    }

    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(getSigingKey()).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }




}
