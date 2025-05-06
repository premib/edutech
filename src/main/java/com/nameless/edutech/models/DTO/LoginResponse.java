package com.nameless.edutech.models.DTO;

public record LoginResponse(
        String token,
        String errorMessage
) {}
