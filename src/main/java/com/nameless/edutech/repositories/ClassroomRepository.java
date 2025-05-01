package com.nameless.edutech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nameless.edutech.models.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {
}
