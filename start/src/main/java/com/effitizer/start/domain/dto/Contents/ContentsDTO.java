package com.effitizer.start.domain.dto.Contents;

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
public class ContentsDTO {
    private Long id;
    private Long user_id;
    private int book_order;
    private String title;
    private String content;
    private String imageURL;
    private LocalDateTime updateDate;
    private ContentsBookDTO book;

    public ContentsDTO(Contents contents) {
        this.id = contents.getId();
        this.user_id = contents.getUser().getId();
        this.book_order = contents.getOrder().getOrder_num();
        this.title = contents.getTitle();
        this.content = contents.getContent();
        this.imageURL = contents.getContentsfiles().toString();
        this.updateDate = contents.getModifiedDate();
        this.book = new ContentsBookDTO(contents.getBook());
    }
}
