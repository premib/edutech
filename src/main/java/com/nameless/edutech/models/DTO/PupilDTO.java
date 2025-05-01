package com.nameless.edutech.models.DTO;

import com.nameless.edutech.models.Classroom;

import java.util.Date;

public record PupilDTO(
        Long id,
        String firstName,
        String lastName,
        Date dob,
        Classroom classroom
) {}
