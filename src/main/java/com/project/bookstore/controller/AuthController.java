package com.project.bookstore.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is open to everyone!";
    }

//    @PostMapping("/login")
//    public String login(@RequestBody LoginRequest request) {
//
//    }
}
