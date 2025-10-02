package com.project.bookstore.controller;

import com.project.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @GetMapping("/admin")
    public String testAdmin() {
        return userService.testAdmin();
    }

    @GetMapping("/admin/secure")
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
