package com.nameless.edutech.repositories;

import com.nameless.edutech.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<User, String> {
}
