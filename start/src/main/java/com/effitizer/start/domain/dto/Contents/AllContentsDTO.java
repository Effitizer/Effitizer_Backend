package com.effitizer.start.domain.dto.Contents;

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

}
