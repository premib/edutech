package com.nameless.edutech.services.impl;

import com.nameless.edutech.DTO.Classroom.ClassroomResponse;
import com.nameless.edutech.mappers.ClassroomMapper;
import com.nameless.edutech.models.Classroom;
import com.nameless.edutech.DTO.Classroom.ClassroomRequest;
import com.nameless.edutech.models.base.Staff;
import com.nameless.edutech.repositories.ClassroomRepository;
import com.nameless.edutech.repositories.StaffRepository;
import com.nameless.edutech.services.ClassroomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;

    private final StaffRepository staffRepository;

    private final ClassroomMapper classroomMapper;

    public ClassroomServiceImpl(ClassroomRepository classroomRepository, StaffRepository staffRepository,
                                ClassroomMapper classroomMapper) {
        this.classroomRepository = classroomRepository;
        this.staffRepository = staffRepository;
        this.classroomMapper = classroomMapper;
    }

    @Override
    public List<ClassroomResponse> getAllClassroom() {
        return this.classroomRepository.findAll().stream()
                .map(classroomMapper::toClassroomResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ClassroomResponse> getClassroomById(int id) {
        return classroomRepository.findById(id).map(classroomMapper::toClassroomResponse);
    }

    @Override
    public ClassroomResponse saveClassroom(ClassroomRequest classroomRequest) {
        Classroom classroom = classroomMapper.toClassroom(classroomRequest);
        Classroom savedClassroom = classroomRepository.save(classroom);

        return classroomMapper.toClassroomResponse(savedClassroom);
    }

    @Override
    public ClassroomResponse updateClassroom(int id, ClassroomRequest classroomRequest) {
        Classroom classroom = classroomRepository.findById(id).orElseThrow();

        classroom.setClassNumber(classroomRequest.getClassNumber());
        classroom.setSection(classroomRequest.getSection());

        long existingStaffId = classroom.getInchargeStaff().getId();
        if (existingStaffId != classroomRequest.getInchargeStaffId()) {
            Staff staff = staffRepository.findById(existingStaffId).orElseThrow();
            classroom.setInchargeStaff(staff);
        }

        Classroom updatedClassroom = classroomRepository.save(classroom);
        return classroomMapper.toClassroomResponse(updatedClassroom);
    }

    @Override
    public void deleteClassroom(int id) {
        classroomRepository.deleteById(id);
    }
}
