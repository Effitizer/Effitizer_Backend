package com.effitizer.start.domain.dto.Book;

import com.effitizer.start.domain.Book;
import com.effitizer.start.domain.Bookcoverfile;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookDTO {
    private Long book_id;
    private String title;
    private String isbn;
    private Long publisher_id;
    private Long writer_id;
    private Long category_id;
    private BookBookCoverfileDTO cover_image;

    public BookDTO(Book book, Bookcoverfile bookcoverfile) {
        this.book_id = book.getId();
        this.title = book.getTitle();
        this.isbn = book.getTitle();
        this.publisher_id = book.getPublisher().getId();
        this.writer_id = book.getWriter().getId();
        this.category_id = book.getCategory().getId();
        this.cover_image = new BookBookCoverfileDTO(bookcoverfile);
    }
}
