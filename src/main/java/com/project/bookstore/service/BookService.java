package com.project.bookstore.service;

import com.project.bookstore.common.ResourceNotFoundException;
import com.project.bookstore.data.BookResponseDto;
import com.project.bookstore.entity.Author;
import com.project.bookstore.entity.Book;
import com.project.bookstore.repository.AuthorRepository;
import com.project.bookstore.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookService {
    private final BookRepository bookRepo;
    private final AuthorRepository authorRepo;

    public BookService(BookRepository bookRepo, AuthorRepository authorRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
    }
    @Cacheable(value = "books", key = "'all'")
    public List<BookResponseDto> getAllBooks() {
        return bookRepo.findAll()
                .stream()
                .map(BookResponseDto::new)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "books", key = "#id")
    public Book getBookById(Long id) {
        System.out.println("Fetching from DB...");
        return bookRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    @CachePut(value = "books", key = "#result.id" )
    public Book createBook(Book book, Long authorId) {
        Author author = authorRepo.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + authorId));
        book.setAuthor(author);
        return bookRepo.save(book);
    }

    @CachePut(value = "books", key = "#id")
    public BookResponseDto updateBook(Long id, Book updated, Long authorId) {
        Book book = getBookById(id);
        book.setTitle(updated.getTitle());
        book.setIsbn(updated.getIsbn());
        book.setPublicationYear(updated.getPublicationYear());
        book.setPrice(updated.getPrice());

        if (authorId != null) {
            Author author = authorRepo.findById(authorId)
                    .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + authorId));
            book.setAuthor(author);
        }

        Book saved = bookRepo.save(book);
        return new BookResponseDto(saved);
    }

    @CacheEvict(value = "books", key = "#id")
    public void deleteBook(Long id) {
        if (!bookRepo.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        bookRepo.deleteById(id);
    }

    public List<Book> getBooksByAuthor(Long authorId) {
        authorRepo.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id: " + authorId + "not found"));
        return bookRepo.findByAuthorId(authorId);
    }
}
