package com.nameless.edutech.controllers;


import com.nameless.edutech.DTO.Classroom.ClassroomRequest;
import com.nameless.edutech.DTO.Classroom.ClassroomResponse;
import com.nameless.edutech.services.ClassroomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {

    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping
    public List<ClassroomResponse> getAllClass() {
        return classroomService.getAllClassroom();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomResponse> getClassById(@PathVariable int id) {
        Optional<ClassroomResponse> aClass = classroomService.getClassroomById(id);
        return aClass.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ClassroomResponse createClass(@RequestBody ClassroomRequest classroomRequest) {
        return classroomService.saveClassroom(classroomRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassroomResponse> updateClass(@PathVariable int id, @RequestBody ClassroomRequest classroomRequest) {
        try {
            ClassroomResponse updatedClass = classroomService.updateClassroom(id, classroomRequest);
            return ResponseEntity.ok(updatedClass);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable int id) {
        classroomService.deleteClassroom(id);
        return ResponseEntity.noContent().build();
    }
}
