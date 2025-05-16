package com.nameless.edutech.repositories;

import com.nameless.edutech.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
