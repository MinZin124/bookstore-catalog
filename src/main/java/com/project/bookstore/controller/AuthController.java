package com.project.bookstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is open to everyone!";
    }

    @GetMapping("/private")
    public String privateEndpoint() {
        return "This is requires authentication";
    }
}
