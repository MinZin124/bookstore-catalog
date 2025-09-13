package com.project.bookstore.service;

import com.project.bookstore.common.ResourceNotFoundException;
import com.project.bookstore.entity.Author;
import com.project.bookstore.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AuthorService {
    private final AuthorRepository authorRepo;

    public AuthorService(AuthorRepository authorRepo) {
        this.authorRepo = authorRepo;
    }

    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));
    }

    public Author createAuthor(Author author) {
        return authorRepo.save(author);
    }


    public Author updateAuthor(Long id, Author updatedAuthor) {
        Author author = getAuthorById(id);
        author.setFirstName(updatedAuthor.getFirstName());
        author.setLastName(updatedAuthor.getLastName());
        author.setBio(updatedAuthor.getBio());
        return authorRepo.save(author);
    }

    public void deleteAuthor(Long id) {
        if (!authorRepo.existsById(id)) {
            throw new ResourceNotFoundException("Author not found with id: " + id);
        }
        authorRepo.deleteById(id);
    }
}

