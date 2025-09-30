package com.project.bookstore.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final Map<String, UserDetails> users = new HashMap<>();

    public CustomUserDetailService() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        users.put("alice", User.withUsername("alice")
                .password(passwordEncoder.encode("password")) // {noop} = no password encoding
                .roles("USER")
                .build());
        users.put("admin", User.withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = users.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return user;
    }
}
