package com.effitizer.start.domain.dto;

import com.effitizer.start.domain.Contents;
import com.effitizer.start.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContentsDTO {
    public Long id;
    public String title;
    public String content;
    public User user_id;

    public ContentsDTO(Contents contents) {
        this.id = contents.id;
        this.user_id = contents.user_id;
        this.title = contents.title;
        this.content = contents.content;
    }
}
