package com.effitizer.start.domain.dto.Contents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContentsDTO {
    private Long id;
    private Long user_id;
    private int book_order;
    private  String title;
    private String content;
    private String category;
    private String imageURL;

}
