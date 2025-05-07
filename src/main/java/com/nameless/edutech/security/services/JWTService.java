package com.nameless.edutech.security.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
    String generateJWT(String subject);
    boolean validateJWT(String jwt, UserDetails userDetails);

    String extractUserName(String token);
}
