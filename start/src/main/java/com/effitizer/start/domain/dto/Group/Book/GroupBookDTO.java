package com.effitizer.start.domain.dto.Group.Book;

import com.effitizer.start.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
public class GroupBookDTO {
    private String title;
    private String writer;
    private String publisher;
    private String coverUrl;

    public GroupBookDTO(Book book) {
        this.title = book.getTitle();
        this.writer = book.getWriter().getName();
        this.publisher = book.getPublisher().getName();
        this.coverUrl = book.getBookcoverfile().getPath();
    }
}
