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
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookResponseDto getBookById(@PathVariable Long id) {
        Book b = bookService.getBookById(id);
        return new BookResponseDto(b);
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
                .body(new BookResponseDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable Long id, @RequestBody BookRequestDto dto) {
        Book updated = new Book();
        updated.setTitle(dto.getTitle());
        updated.setIsbn(dto.getIsbn());
        updated.setPublicationYear(dto.getPublicationYear());
        updated.setPrice(dto.getPrice());

        BookResponseDto saved = bookService.updateBook(id, updated, dto.getAuthorId());

        BookResponseDto response = new BookResponseDto(
                saved);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/author/{authorId}")
    public List<BookResponseDto> getBooksByAuthor(@PathVariable Long authorId) {
        return bookService.getBooksByAuthor(authorId).stream()
                .map(BookResponseDto::new)
                .collect(Collectors.toList());
    }
}
