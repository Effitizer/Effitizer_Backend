package com.effitizer.start.service;

import com.effitizer.start.domain.Category;
import com.effitizer.start.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Category saveCategory(String name) {
        Category category  = new Category();
        category.setName(name);
        categoryRepository.save(category);
        return category;
    }

    /**
     *  전체 Category 객체 조회
     */
    public List<Category> findCategorys() {
        return categoryRepository.findAll();
    }
}
