package com.example.hongpark.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class MemberController {
    @GetMapping("/signup")
    public String addMember() {
        return "members/new";
    }

    @PostMapping("/members")

}
