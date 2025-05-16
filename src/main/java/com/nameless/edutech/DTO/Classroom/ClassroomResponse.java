package com.nameless.edutech.DTO.Classroom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomResponse {
    private String classNumber;

    private String section;

    private ClassInchargeStaff inchargeStaff;

    private List<ClassStudentInfo> students;
}
