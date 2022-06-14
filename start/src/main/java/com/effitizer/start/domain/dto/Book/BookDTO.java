package com.effitizer.start.domain.dto.Book;

import com.effitizer.start.domain.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookDTO {
    private Long id;
    private String title;
    private String isbn;
    private Long publisher_id;
    private Long writer_id;
    private Long category_id;
    private String cover_path;

    public BookDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.isbn = book.getTitle();
        this.publisher_id = book.getPublisher().getId();
        this.writer_id = book.getWriter().getId();
        this.category_id = book.getCategory().getId();
        this.cover_path = "string";
    }
}
