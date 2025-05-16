package com.nameless.edutech.repositories;

import com.nameless.edutech.models.base.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
}
