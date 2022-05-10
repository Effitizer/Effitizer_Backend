package com.effitizer.start.domain.dto.Contents;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ContentsRequest {
    private String title;
    private String content;
    private int user_id;
    private int book_order;
    private String image;

}
