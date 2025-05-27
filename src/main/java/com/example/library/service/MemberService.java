package com.example.library.service;

import com.example.library.entity.member;
import com.example.library.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public member registerMember(member member) {
        if (memberRepository.existsByEmail(member.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }
        return memberRepository.save(member);
    }

    public List<member> getAllMembers() {
        return memberRepository.findAll();
    }

    public member getMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Member not found"));
    }
}