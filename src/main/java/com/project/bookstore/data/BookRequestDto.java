package com.project.bookstore.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookRequestDto {
    private String title;
    private String isbn;
    private Integer publicationYear;
    private Double price;
    private Long authorId;
}
