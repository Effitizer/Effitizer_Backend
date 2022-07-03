package com.effitizer.start.domain.dto.Book;

import com.effitizer.start.domain.Bookcoverfile;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookBookCoverfileDTO {
    private Long cover_image_id;
    private Long size;
    private String extend;
    private String path;

    public BookBookCoverfileDTO(Bookcoverfile bookcoverfile){
        this.cover_image_id = bookcoverfile.getId();
        this.size = bookcoverfile.getSize();
        this.extend = bookcoverfile.getExtend();
        this.path = bookcoverfile.getPath();
    }
}
