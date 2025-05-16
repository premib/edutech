package com.nameless.edutech.DTO.Student;

import com.nameless.edutech.DTO.Common.HumanDTO;
import com.nameless.edutech.models.enums.ActivityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse extends HumanDTO {
    private StudentClassInfo classroom;

    private ActivityStatus activityStatus;

    private int rollNumber;
}
