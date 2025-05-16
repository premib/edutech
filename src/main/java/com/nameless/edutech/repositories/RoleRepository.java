package com.nameless.edutech.repositories;

import com.nameless.edutech.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
