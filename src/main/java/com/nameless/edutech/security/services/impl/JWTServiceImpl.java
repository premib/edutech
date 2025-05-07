package com.nameless.edutech.security.services.impl;

import com.nameless.edutech.security.services.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {

    private Key secretKey;

    public JWTServiceImpl() {
        try {
            secretKey = KeyGenerator.getInstance("HmacSHA256").generateKey();
        } catch (NoSuchAlgorithmException e) {
            secretKey = null;
        }
    }

    /**
     * @return
     */
    @Override
    public String generateJWT(String subject) {

        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .and()
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey()  {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getEncoded());
        System.out.println("key used to sign jwt" + Base64.getEncoder().encodeToString(key.getEncoded()));

        return key;
    }

    /**
     * @param jwt
     * @return
     */
    @Override
    public boolean validateJWT(String jwt, UserDetails userDetails) {
        final String userName = extractUserName(jwt);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(jwt));
    }

    public String extractUserName(String token){
        // extract the username from jwt token
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpired(String token)  {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
