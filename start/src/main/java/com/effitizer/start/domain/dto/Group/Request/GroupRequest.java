package com.effitizer.start.domain.dto.Group.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupRequest {
    private String title;
    private List<GroupContentsRequest> contents;
}
