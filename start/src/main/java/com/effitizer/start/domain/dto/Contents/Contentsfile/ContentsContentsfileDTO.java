package com.effitizer.start.domain.dto.Contents.Contentsfile;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ContentsContentsfileDTO {
    private Long contents_image_id;
    private Long size;
    private String extend;
    private String path;
}
