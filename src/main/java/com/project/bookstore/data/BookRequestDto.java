package com.project.bookstore.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookRequestDto {
    @Schema(description = "Title of the book", example = "Harry Potter")
    private String title;

    @Schema(description = "ISBN code of the book", example = "978-3-16-148410-0")
    private String isbn;

    @Schema(description = "Year the book was published", example = "1997")
    private Integer publicationYear;

    @Schema(description = "Price of the book", example = "19.99")
    private Double price;

    @Schema(description = "ID of the author", example = "1")
    private Long authorId;
}
