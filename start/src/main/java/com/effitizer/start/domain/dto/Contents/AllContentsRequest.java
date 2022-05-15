package com.effitizer.start.domain.dto.Contents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllContentsRequest {
    private String title;
    private String isbn;
    private String writer;
    private String publisher;
    private Long category_id;
    private Long user_id;
    private List<ContentsRequest> contents;

}
