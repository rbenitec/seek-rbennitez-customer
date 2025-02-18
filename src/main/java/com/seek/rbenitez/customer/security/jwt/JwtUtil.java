package com.seek.rbenitez.customer.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
@Slf4j
public class JwtUtil {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.time.expiration}")
    private String timeExpiration;

    /**
     * Method by generated token
     *
     * @param username
     * @return
     */

    public String generateAccessToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
                .signWith(getSignature(), SignatureAlgorithm.HS256)
                .compact();

    }

    // Validar token de acceso
    public boolean validateToken(String token) {
        try {
            Jwts
                    .parserBuilder()
                    .setSigningKey(getSignature())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (Exception e) {
            log.error("Error al validar token: {}", e.getMessage());
            return false;
        }
    }

    //Obtener el usuario del token
    public String getUsernameOfToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    // Obtener un solo claims
    public <T> T getClaim(String token, Function<Claims, T> claimsFunctions) {
        Claims claims = getAllClaims(token);
        return claimsFunctions.apply(claims);
    }

    //Obtener los claims, osea la info que viene dentro del token
    public Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignature())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // obtener firma del token
    public Key getSignature() {
        byte[] secretBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}