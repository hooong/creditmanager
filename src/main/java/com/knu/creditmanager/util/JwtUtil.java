package com.knu.creditmanager.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtUtil {

    private Key key;

    public JwtUtil(String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String createToken(String studentId, String name) {
        JwtBuilder builder = Jwts.builder()
                .claim("studentId", studentId)
                .claim("name", name);

        return builder
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims getClaims(String token){
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }
}
