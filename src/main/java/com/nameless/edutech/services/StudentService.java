package com.nameless.edutech.services;

import com.nameless.edutech.DTO.Student.StudentRequest;
import com.nameless.edutech.DTO.Student.StudentResponse;
import com.nameless.edutech.DTO.StudentDTO;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentResponse> getAllStudents();
    Optional<StudentResponse> getStudentById(Long id);
    StudentResponse saveStudent(StudentRequest studentRequest);
    StudentResponse updateStudent(Long id, StudentRequest studentRequest);
    void deleteStudent(Long id);
}
