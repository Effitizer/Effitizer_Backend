package com.effitizer.start.domain.dto.Group.Book;

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
}
