package com.project.bookstore.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookResponseDto {
    private Long id;
    private String title;
    private String isbn;
    private Integer publicationYear;
    private Double price;
    private Long authorId;

    public BookResponseDto(Long id, String title, String isbn, Integer publicationYear, Double price, Long authorId) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.price = price;
        this.authorId = authorId;
    }
}