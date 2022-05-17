package com.effitizer.start.domain.dto.Group.Contents;

import com.effitizer.start.domain.Contents;
import com.effitizer.start.domain.Group;
import com.effitizer.start.domain.dto.Group.Book.GroupBookDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GroupContentsDTO {
    private Long id;
    private String title;
    private int order;
    private GroupBookDTO book;

    public GroupContentsDTO(Contents contents){
        this.id = contents.getId();
        this.title = contents.getTitle();
        this.order = contents.getOrder().getOrder_num();
        this.book = new GroupBookDTO(contents.getBook());
    }
}
