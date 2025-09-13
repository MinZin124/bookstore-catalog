package com.project.bookstore.service;

import com.project.bookstore.common.ResourceNotFoundException;
import com.project.bookstore.entity.Author;
import com.project.bookstore.entity.Book;
import com.project.bookstore.repository.AuthorRepository;
import com.project.bookstore.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BookService {
    private final BookRepository bookRepo;
    private final AuthorRepository authorRepo;

    public BookService(BookRepository bookRepo, AuthorRepository authorRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    public Book createBook(Book book, Long authorId) {
        Author author = authorRepo.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + authorId));
        book.setAuthor(author);
        return bookRepo.save(book);
    }

    public Book updateBook(Long id, Book updated, Long authorId) {
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

        return bookRepo.save(book);
    }

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
