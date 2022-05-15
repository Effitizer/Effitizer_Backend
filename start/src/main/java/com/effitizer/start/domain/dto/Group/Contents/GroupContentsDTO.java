package com.effitizer.start.domain.dto.Group.Contents;

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
}
