package com.nameless.edutech.DTO.Classroom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomRequest {
    private int id;

    private String classNumber;

    private String section;

    private long inchargeStaffId;
}