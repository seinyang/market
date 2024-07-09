package com.example.swagger.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    // 토큰의 유효 기간(초) 반환
    @Getter
    @Value("${jwt.token-validity-in-seconds}")
    private long tokenValidityInSeconds;

    @Getter
    @Value("${jwt.header}")
    private String header;

    public String generateToken(String id) {
        return Jwts.builder()
                .setSubject(id)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidityInSeconds * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }


    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUserIdFromToken(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        if (validateToken(token)) {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            return claims.getExpiration().before(new Date());
        } else {
            return true;
        }
    }
    }