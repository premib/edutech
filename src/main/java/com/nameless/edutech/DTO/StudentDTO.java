package com.nameless.edutech.DTO;

import com.nameless.edutech.models.Classroom;
import com.nameless.edutech.models.embeddable.Contact;

import java.time.LocalDate;

public record StudentDTO(
        Long id,
        String firstName,
        String lastName,
        LocalDate dob,
        Classroom classroom,
        Contact contact
) {}
