package com.project.bookstore.controller;

import com.project.bookstore.data.AuthorRequestDto;
import com.project.bookstore.data.AuthorResponseDto;
import com.project.bookstore.entity.Author;
import com.project.bookstore.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<AuthorResponseDto> getAllAuthors() {
        return authorService.getAllAuthors()
                .stream()
                .map(a -> new AuthorResponseDto(a.getId(), a.getFirstName(), a.getLastName(), a.getBio()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AuthorResponseDto getAuthorById(@PathVariable Long id) {
        Author a = authorService.getAuthorById(id);
        return new AuthorResponseDto(a.getId(), a.getFirstName(), a.getLastName(), a.getBio());
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDto> createAuthor(@RequestBody AuthorRequestDto dto) {
        Author author = new Author();
        author.setFirstName(dto.getFirstName());
        author.setLastName(dto.getLastName());
        author.setBio(dto.getBio());

        Author saved = authorService.createAuthor(author);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthorResponseDto(saved.getId(), saved.getFirstName(), saved.getLastName(), saved.getBio()));
    }

    @PutMapping("/{id}")
    public AuthorResponseDto updateAuthor (@PathVariable Long id, @RequestBody AuthorRequestDto dto) {
        Author updated =  new Author();
        updated.setFirstName(dto.getFirstName());
        updated.setLastName(dto.getLastName());
        updated.setBio(dto.getBio());
        Author saved = authorService.updateAuthor(id, updated);
        return new AuthorResponseDto(saved.getId(), saved.getFirstName(), saved.getLastName(), saved.getBio());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

}

