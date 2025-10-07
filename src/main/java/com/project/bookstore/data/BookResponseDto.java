package com.project.bookstore.data;

import com.project.bookstore.entity.Book;
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

    public BookResponseDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.isbn = book.getIsbn();
        this.publicationYear = book.getPublicationYear();
        this.price = book.getPrice();
        this.authorId = book.getId();
    }

    public BookResponseDto(BookResponseDto saved) {
    }
}