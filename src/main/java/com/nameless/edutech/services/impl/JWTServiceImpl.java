package com.nameless.edutech.services.impl;

import com.nameless.edutech.services.JWTService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTServiceImpl implements JWTService {
    /**
     * @return
     */
    @Override
    public String generateJWT(String subject) {

        Map<String, Object> claims = new HashMap<>();

        try {
            return Jwts.builder()
                    .claims()
                    .add(claims)
                    .subject(subject)
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                    .and()
                    .signWith(getKey())
                    .compact();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private Key getKey() throws NoSuchAlgorithmException {
        Key key = Keys.hmacShaKeyFor(KeyGenerator.getInstance("HmacSHA256").generateKey().getEncoded());
        System.out.println("key used to sign jwt" + key);
        return key;
    }

    /**
     * @param jwt
     * @return
     */
    @Override
    public String validateJWT(String jwt) {
        return "";
    }
}
