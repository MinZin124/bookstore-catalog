package com.project.bookstore.controller;

import com.project.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @GetMapping("/admin")
    public String securedAdmin() {
        return userService.adminSecureOperation();
    }

    @GetMapping("/user")
    public String preAuthUser() {
        return userService.userOrAdminOperation();
    }

    @GetMapping("/profile/{username}")
    public String profile(@PathVariable String username) {
        return userService.viewOwnProfile(username);
    }
}
