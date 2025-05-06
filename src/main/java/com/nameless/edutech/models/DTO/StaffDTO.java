package com.nameless.edutech.models.DTO;

import jakarta.persistence.Column;

import java.time.LocalDate;
import java.util.Date;

public record StaffDTO(
        long id,
        String firstName,
        String lastName,
        LocalDate dob,
        String photoUrl
) {
}
