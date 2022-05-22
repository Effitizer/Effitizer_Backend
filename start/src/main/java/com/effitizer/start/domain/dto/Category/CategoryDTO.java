package com.effitizer.start.domain.dto.Category;

import com.effitizer.start.domain.Category;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CategoryDTO {
    private Long id;
    private String name;

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
