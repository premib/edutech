package com.nameless.edutech.controllers;


import com.nameless.edutech.models.DTO.ClassroomDTO;
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
    public List<ClassroomDTO> getAllClass() {
        return classroomService.getAllClassroom();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomDTO> getClassById(@PathVariable int id) {
        Optional<ClassroomDTO> aClass = classroomService.getClassroomById(id);
        return aClass.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ClassroomDTO createClass(@RequestBody ClassroomDTO classroomDTO) {
        return classroomService.saveClassroom(classroomDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassroomDTO> updateClass(@PathVariable int id, @RequestBody ClassroomDTO classroomDTO) {
        try {
            ClassroomDTO updatedClass = classroomService.updateClassroom(id, classroomDTO);
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
