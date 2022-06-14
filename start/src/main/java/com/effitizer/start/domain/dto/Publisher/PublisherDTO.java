package com.effitizer.start.domain.dto.Publisher;

import com.effitizer.start.domain.Category;
import com.effitizer.start.domain.Publisher;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PublisherDTO {
    private Long id;
    private String name;

    public PublisherDTO(Publisher publisher) {
        this.id = publisher.getId();
        this.name = publisher.getName();
    }
}
