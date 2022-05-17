package com.effitizer.start.domain.dto.Contents;

import com.effitizer.start.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AllContentsDTO {
    private Long book_id;
    private String title;
    private String isbn;
    private String writer;
    private String publisher;
    private List<OnlyContentsDTO> contents;

    public AllContentsDTO(Book book,List<OnlyContentsDTO> contents) {
        this.book_id = book.getId();
        this.title = book.getTitle();
        this.isbn = book.getIsbn();
        this.writer = book.getWriter().getName();
        this.publisher = book.getPublisher().getName();
        this.contents = contents;
    }

}
