package com.effitizer.start.domain.dto.Group;

import com.effitizer.start.domain.Group;
import com.effitizer.start.domain.dto.Group.Contents.GroupContentsDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AllGroupDTO {
    private Long id;
    private String title;
    private List<GroupContentsDTO> items;

    public AllGroupDTO(Group group,List<GroupContentsDTO> items) {
        this.id = group.getId();
        this.title = group.getTitle();
        this.items = items;
    }
}
