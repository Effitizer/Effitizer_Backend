package com.effitizer.start.domain.dto.Contents.Book;

import com.effitizer.start.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContentsBookDTO {
    private Long book_id;
    private String title;
    private String writer;
    private String publisher;
    private String category;
    private String coverURL;

    public ContentsBookDTO(Book book) {
        this.book_id = book.getId();
        this.title = book.getTitle();
        this.writer = book.getWriter().getName();
        this.publisher = book.getPublisher().getName();
        this.category = book.getCategory().getName();
        this.coverURL = "coverURL";
    }
}
