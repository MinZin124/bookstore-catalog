package com.project.bookstore.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthorResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;

    public AuthorResponseDto(Long id, String firstName, String lastName, String bio) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
    }
}

