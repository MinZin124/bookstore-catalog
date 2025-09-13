package com.project.bookstore.controller;


import com.project.bookstore.data.BookRequestDto;
import com.project.bookstore.data.BookResponseDto;
import com.project.bookstore.entity.Book;
import com.project.bookstore.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController (BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookResponseDto> getAllBooks() {
        return bookService.getAllBooks().stream()
                .map(b -> new BookResponseDto(b.getId(), b.getTitle(), b.getIsbn(), b.getPublicationYear(), b.getPrice(), b.getAuthor().getId()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookResponseDto getBookById(@PathVariable Long id) {
        Book b = bookService.getBookById(id);
        return new BookResponseDto(b.getId(), b.getTitle(), b.getIsbn(), b.getPublicationYear(), b.getPrice(), b.getAuthor().getId());
    }

    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@RequestBody BookRequestDto dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setPublicationYear(dto.getPublicationYear());
        book.setPrice(dto.getPrice());
        Book saved = bookService.createBook(book, dto.getAuthorId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BookResponseDto(saved.getId(), saved.getTitle(), saved.getIsbn(), saved.getPublicationYear(), saved.getPrice(), saved.getAuthor().getId()));
    }

    @PutMapping("/{id}")
    public BookResponseDto updateBook(@PathVariable Long id, @RequestBody BookRequestDto dto) {
        Book updated = new Book();
        updated.setTitle(dto.getTitle());
        updated.setIsbn(dto.getIsbn());
        updated.setPublicationYear(dto.getPublicationYear());
        updated.setPrice(dto.getPrice());
        Book saved = bookService.updateBook(id, updated, dto.getAuthorId());
        return new BookResponseDto(saved.getId(), saved.getTitle(), saved.getIsbn(), saved.getPublicationYear(), saved.getPrice(), saved.getAuthor().getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/author/{authorId}")
    public List<BookResponseDto> getBooksByAuthor(@PathVariable Long authorId) {
        return bookService.getBooksByAuthor(authorId).stream()
                .map(b -> new BookResponseDto(b.getId(), b.getTitle(), b.getIsbn(), b.getPublicationYear(), b.getPrice(), b.getAuthor().getId()))
                .collect(Collectors.toList());
    }
}
