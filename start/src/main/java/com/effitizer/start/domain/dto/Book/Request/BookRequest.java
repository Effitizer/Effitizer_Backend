package com.effitizer.start.domain.dto.Book.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookRequest {
    private String title;
    private String isbn;
    private Long publisher_id;
    private Long writer_id;
    private Long category_id;
}
