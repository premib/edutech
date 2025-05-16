package com.nameless.edutech.DTO.User;


public record LoginResponse(
        String token,
        String errorMessage
) {}
