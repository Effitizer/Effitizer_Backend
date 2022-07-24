package com.effitizer.start.domain.dto.Contents.Request;

import com.effitizer.start.domain.Contents;
import com.effitizer.start.domain.dto.Contents.Book.ContentsBookDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OnlyContentsRequest {
    private Long id;
    private Long user_id;
    private int book_order;
    private String title;
    private String content;
    private String imageURL;

    public OnlyContentsRequest(Contents contents) {
        this.id = contents.getId();
        this.user_id = contents.getUser().getId();
        this.book_order = contents.getOrder().getOrderNum();
        this.title = contents.getTitle();
        this.content = contents.getContent();
        this.imageURL = contents.getContentsfiles().toString();
    }
}