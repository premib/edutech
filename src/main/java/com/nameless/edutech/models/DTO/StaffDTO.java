package com.nameless.edutech.models.DTO;

import jakarta.persistence.Column;

import java.util.Date;

public record StaffDTO(
        long id,
        String firstName,
        String lastName,
        Date dob,
        String photoUrl
) {
}
