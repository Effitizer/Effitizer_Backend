package com.effitizer.start.domain.dto.Group;

import com.effitizer.start.domain.Group;
import com.effitizer.start.domain.dto.Group.Book.GroupBookDTO;
import com.effitizer.start.domain.dto.Group.Contents.GroupContentsDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GroupDTO {
    private Long id;
    private String title;
    private List<GroupContentsDTO> contents;

    public GroupDTO(Group group, List<GroupContentsDTO> contents) {
        this.id = group.getId();
        this.title = group.getTitle();
        this.contents = contents;
    }

}
