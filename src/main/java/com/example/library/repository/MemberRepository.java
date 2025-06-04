package com.example.library.repository;

import com.example.library.entity.member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<member, Long> {
    // Inherits basic DB operations for Member entity (save, delete, findById)

    boolean existsByEmail(String email);
    // Checks if a member with the given email already exists in DB.
}