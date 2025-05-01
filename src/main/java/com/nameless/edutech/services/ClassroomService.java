package com.nameless.edutech.services;

import com.nameless.edutech.models.DTO.ClassroomDTO;

import java.util.List;
import java.util.Optional;

public interface ClassroomService {
    List<ClassroomDTO> getAllClassroom();
    Optional<ClassroomDTO> getClassroomById(int id);
    ClassroomDTO saveClassroom(ClassroomDTO classroomDTO);
    ClassroomDTO updateClassroom(int id, ClassroomDTO classroomDTO);
    void deleteClassroom(int id);
}
