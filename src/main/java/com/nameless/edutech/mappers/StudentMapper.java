package com.nameless.edutech.mappers;

import com.nameless.edutech.DTO.Student.StudentRequest;
import com.nameless.edutech.DTO.Student.StudentResponse;
import com.nameless.edutech.models.Student;
import com.nameless.edutech.repositories.StudentRepository;
import org.mapstruct.Mapper;

//@Component
@Mapper(componentModel = "spring")
public abstract class StudentMapper {

    public abstract StudentResponse toStudentResponse(Student student);

    public abstract Student toStudent(StudentResponse studentResponse);

    public abstract StudentRequest toStudentRequest(StudentResponse studentResponse);

    public abstract Student toStudent(StudentRequest studentRequest);
}