package com.effitizer.start.domain.dto.Contents;

import com.effitizer.start.domain.Contents;
import com.effitizer.start.domain.dto.Contents.Book.ContentsBookDTO;
import com.effitizer.start.domain.dto.Contents.Contentsfile.ContentsContentsfileDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContentsDTO {
    private Long id;
    private int book_order;
    private String title;
    private String content;
    private List<ContentsContentsfileDTO> imageURLs;
    private LocalDateTime updateDate;

    public ContentsDTO(Contents contents) {
        this.id = contents.getId();
        this.book_order = contents.getOrder().getOrder_num();
        this.title = contents.getTitle();
        this.content = contents.getContent();
        this.updateDate = contents.getModifiedDate();
    }

    public ContentsDTO(Contents contents, List<ContentsContentsfileDTO> contentsContentsfileDTOs) {
        this.id = contents.getId();
        this.book_order = contents.getOrder().getOrder_num();
        this.title = contents.getTitle();
        this.content = contents.getContent();
        this.imageURLs = contentsContentsfileDTOs;
        this.updateDate = contents.getModifiedDate();
    }
}
