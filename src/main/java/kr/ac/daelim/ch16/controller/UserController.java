package kr.ac.daelim.ch16.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("")
    public String getTest() {
        return "Get User Test";
    }

    @PostMapping("/admin")
    public String adminTest() {
        return "Admin Test";
    }
}
