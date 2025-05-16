package com.nameless.edutech.repositories;

import com.nameless.edutech.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
