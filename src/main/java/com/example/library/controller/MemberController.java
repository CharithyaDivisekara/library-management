package com.example.library.controller;

import com.example.library.entity.member;
import com.example.library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping
    public member addMember(@RequestBody member member) {
        return memberService.addMember(member);
    }

    @GetMapping
    public List<member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public member getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }
}
