package com.effitizer.start.domain.dto.Contents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContentsTestRequest {
    private String title;
    private String isbn;
    private String writer;
    private String publisher;
    private Long category_id;
    private Long user_id;
}
