package com.nameless.edutech.services;

import com.nameless.edutech.DTO.Classroom.ClassroomRequest;
import com.nameless.edutech.DTO.Classroom.ClassroomResponse;

import java.util.List;
import java.util.Optional;

public interface ClassroomService {
    List<ClassroomResponse> getAllClassroom();
    Optional<ClassroomResponse> getClassroomById(int id);
    ClassroomResponse saveClassroom(ClassroomRequest classroomRequest);
    ClassroomResponse updateClassroom(int id, ClassroomRequest classroomRequest);
    void deleteClassroom(int id);
}
