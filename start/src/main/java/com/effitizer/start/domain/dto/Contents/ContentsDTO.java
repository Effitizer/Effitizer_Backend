package com.effitizer.start.domain.dto.Contents;

import com.effitizer.start.domain.Contents;
import com.effitizer.start.domain.dto.Contents.Book.ContentsBookDTO;
import com.effitizer.start.domain.dto.Contents.Contentsfile.ContentsContentsfileDTO;
import lombok.*;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentsDTO {
    private Long id;
    private int book_order;
    private String title;
    private String content;
    private List<ContentsContentsfileDTO> imageURLs;
    private LocalDateTime updateDate;

    public ContentsDTO(Contents contents) {
        this.id = contents.getId();
        this.book_order = contents.getOrder().getOrderNum();
        this.title = contents.getTitle();
        this.content = contents.getContent();
        this.updateDate = contents.getModifiedDate();
    }

    public ContentsDTO(Contents contents, List<ContentsContentsfileDTO> contentsContentsfileDTOs) {
        this.id = contents.getId();
        this.book_order = contents.getOrder().getOrderNum();
        this.title = contents.getTitle();
        this.content = contents.getContent();
        this.imageURLs = contentsContentsfileDTOs;
        this.updateDate = contents.getModifiedDate();
    }


    public Page<ContentsDTO> toDtoList(Page<Contents> contentsList){
        Page<ContentsDTO> boardDtoList = contentsList.map(m ->ContentsDTO.builder()
                .id(m.getId())
                .book_order(m.getOrder().getOrderNum())
                .title(m.getTitle())
                .content(m.getContent())
                .updateDate(m.getModifiedDate())
                .build());
        return boardDtoList;
    }
}
