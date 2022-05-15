package com.effitizer.start.domain.dto.Contents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class ContentsRequest {
    private String title;
    private String content;
    private int order;
    private MultipartFile image;

}
