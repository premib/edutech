package com.nameless.edutech.services;

import java.security.NoSuchAlgorithmException;

public interface JWTService {
    String generateJWT(String subject);
    String validateJWT(String jwt);
}
