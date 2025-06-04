package com.example.library.service;

import com.example.library.entity.member;
import com.example.library.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Marks this clz as a service component in Spring
@RequiredArgsConstructor // Automatically creates a constructor for final fields (like memberRepository)
public class MemberService {
    private final MemberRepository memberRepository;
    // Repository to access member data in the DB

    public member registerMember(member member) {
         // Method to add a new member to the system

        if (memberRepository.existsByEmail(member.getEmail())) {
            // Check if email is already registered
            throw new IllegalArgumentException("Email already registered");
            // If yes, stop and throw an error
        }
        return memberRepository.save(member);// Save the new member and return it
    }

    public member addMember(member member) {// your logic here
    return memberRepository.save(member); // if you're saving it in DB
}



    public List<member> getAllMembers() {  // Method to get a list of all members
        return memberRepository.findAll(); // Return all members from the DB
    }

    public member getMemberById(Long id) {  // Method to find a member by their ID
        return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Member not found"));
        // Return the member if found, otherwise throw an error
    }
}