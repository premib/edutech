package com.nameless.edutech.services.impl;

import com.nameless.edutech.DTO.Student.StudentRequest;
import com.nameless.edutech.DTO.Student.StudentResponse;
import com.nameless.edutech.DTO.StudentDTO;
import com.nameless.edutech.mappers.StudentMapper;
import com.nameless.edutech.models.Classroom;
import com.nameless.edutech.models.Student;
import com.nameless.edutech.repositories.ClassroomRepository;
import com.nameless.edutech.repositories.StudentRepository;
import com.nameless.edutech.services.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final ClassroomRepository classroomRepository;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper, ClassroomRepository classroomRepository) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.classroomRepository = classroomRepository;
    }

    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toStudentResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StudentResponse> getStudentById(Long id) {
        return studentRepository.findById(id).map(studentMapper::toStudentResponse);
    }

    @Override
    public StudentResponse saveStudent(StudentRequest studentRequest) {
        Student student = studentMapper.toStudent(studentRequest);
        Student savedStudent = studentRepository.save(student);

        return studentMapper.toStudentResponse(savedStudent);
    }

    @Override
    public StudentResponse updateStudent(Long id, StudentRequest studentRequest) {
        Student student = studentRepository.findById(id).orElseThrow();
        Classroom classroom = classroomRepository.getReferenceById(studentRequest.getClassId());

        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setDob(studentRequest.getDob());
        student.setClassroom(classroom);

        Student updatedStudent = studentRepository.save(student);

        return studentMapper.toStudentResponse(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
