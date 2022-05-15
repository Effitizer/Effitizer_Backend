package com.effitizer.start.domain.dto.Group;

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
}
