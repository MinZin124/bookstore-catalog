package com.project.bookstore.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "Welcome to home page";
    }

    @GetMapping("/whoami")
    public String whoAmi() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username =auth.getName();
        return "You are logged in as: " + username;
    }
}
