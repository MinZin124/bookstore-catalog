package com.project.bookstore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "books")
@Setter
@Getter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String title;

    @Column(unique=true, nullable=false)
    private String isbn;

    private Integer publicationYear;

    @Column(nullable=false)
    private Double price;

    @ManyToOne
    @JoinColumn(name="author_id", nullable=false)
    private Author author;

}
