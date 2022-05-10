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
    private String book_name;
    private String writer_name;
    private String publisher_name;
    private Long category_id;
    private List<ContentsRequest> contents;

}
