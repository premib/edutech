package com.nameless.edutech.DTO.Student;

import com.nameless.edutech.DTO.Common.HumanDTO;
import com.nameless.edutech.models.Classroom;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequest extends HumanDTO {
    private long id;

    private int classId;

    private String admissionNumber;

    private LocalDate admissionDate;

    private int rollNumber;
}
