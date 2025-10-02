package com.project.bookstore.common;

import com.project.bookstore.entity.AppUser;
import com.project.bookstore.entity.Role;
import com.project.bookstore.repository.RoleRepository;
import com.project.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

//@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseGet(() -> roleRepository.save(new Role(null, "ROLE_ADMIN")));

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> roleRepository.save(new Role(null, "ROLE_USER")));

        userRepository.findByUsername("admin").orElseGet(() -> {
            AppUser admin = new AppUser();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(Set.of(adminRole));
            return userRepository.save(admin);
        });

        userRepository.findByUsername("alice").orElseGet(() -> {
            AppUser alice = new AppUser();
            alice.setUsername("alice");
            alice.setPassword(passwordEncoder.encode("password"));
            alice.setRoles(Set.of(userRole));
            return userRepository.save(alice);
        });
    }
}
