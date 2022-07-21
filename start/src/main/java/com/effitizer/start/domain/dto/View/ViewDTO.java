package com.effitizer.start.domain.dto.View;

import com.effitizer.start.domain.View;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class ViewDTO {

    private Long id;
    private String data;

    public ViewDTO(View view) {
        this.id = view.getId();
        this.data = view.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
}
