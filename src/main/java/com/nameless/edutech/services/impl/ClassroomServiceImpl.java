package com.nameless.edutech.services.impl;

import com.nameless.edutech.models.Classroom;
import com.nameless.edutech.models.DTO.ClassroomDTO;
import com.nameless.edutech.repositories.ClassroomRepository;
import com.nameless.edutech.services.ClassroomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;

    public ClassroomServiceImpl(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    @Override
    public List<ClassroomDTO> getAllClassroom() {
        return this.classroomRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ClassroomDTO> getClassroomById(int id) {
        return classroomRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public ClassroomDTO saveClassroom(ClassroomDTO classroomDTO) {
        Classroom classroom = convertToEntity(classroomDTO);
        Classroom savedClassroom = classroomRepository.save(classroom);

        return convertToDTO(savedClassroom);
    }

    @Override
    public ClassroomDTO updateClassroom(int id, ClassroomDTO classroomDTO) {
        Classroom classroom = classroomRepository.findById(id).orElseThrow();

        classroom.setClassNumber(classroomDTO.classNumber());
        classroom.setSection(classroomDTO.section());
        classroom.setInchargeStaff(classroomDTO.inchargeStaff());

        Classroom updatedClassroom = classroomRepository.save(classroom);
        return convertToDTO(updatedClassroom);
    }

    @Override
    public void deleteClassroom(int id) {
        classroomRepository.deleteById(id);
    }

    private ClassroomDTO convertToDTO(Classroom classroom) {
        return new ClassroomDTO(classroom.getId(), classroom.getClassNumber(), classroom.getSection(), classroom.getInchargeStaff());
    }

    private Classroom convertToEntity(ClassroomDTO classroomDTO) {
        Classroom classroom = new Classroom();

        classroom.setClassNumber(classroomDTO.classNumber());
        classroom.setSection(classroomDTO.section());
        classroom.setInchargeStaff(classroomDTO.inchargeStaff());

        return classroom;
    }
}
