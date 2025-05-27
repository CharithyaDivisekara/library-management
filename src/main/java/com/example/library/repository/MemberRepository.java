package com.example.library.repository;

import com.example.library.entity.member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<member, Long> {
    boolean existsByEmail(String email);
}