package com.project.bookstore.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.swing.text.Document;

@Service
public class UserService{

    @Secured("ROLE_ADMIN")
    public String testAdmin() {
        return "Admin dashboard";
    }

    @Secured("ROLE_ADMIN")
    public String adminSecureOperation() {
        return "Admin Operation via @Secured";
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String userOrAdminOperation() {
        return "Operation via @PreAuthorize";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userRead() {
        return "Public Books for user";
    }

    @PreAuthorize("#username == authentication.name")
    public String viewOwnProfile(String username) {
        return "Profile of: " + username;
    }
    @PostAuthorize("returnObject.owner == authentication.name")
    public Document getDocument(Document doc) {
        return doc;
    }

}
