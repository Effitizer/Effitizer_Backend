package com.effitizer.start.domain.dto.Contents.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContentsRequest {
    private String isbn;
    private Long user_id;
    private String title;
    private String content;
    private int book_order;
}
