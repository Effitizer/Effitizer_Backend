package com.effitizer.start.service;

import com.effitizer.start.domain.Category;
import com.effitizer.start.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Category saveCategory(String name) {
        // 중복 데이터 검사
        categoryRepository.findByName(name)
                .ifPresent(c -> {
                    log.info("CategoryService: 중복 데이터 ---------------------");
                    throw new IllegalStateException("이미 존재하는 데이터입니다.");
                });

        Category category  = new Category(name);
        categoryRepository.save(category);
        return category;
    }

    /**
     * id로 Category 조회
     */
    public Category findCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalStateException("카테고리 정보가 올바르지 않습니다."));
    }

    /**
     *  전체 Category 객체 조회
     */
    public List<Category> findCategorys() {
        return categoryRepository.findAll();
    }

    /**
     * 카테고리 수정
     */
    public Category editCategory(Long category_id, String name) {
        Category category = findCategoryById(category_id);
        category.setName(name);
        return category;
    }

    /**
     * 카테고리 카페
     */
    public void deleteCategory(Long category_id) {
        Category category = findCategoryById(category_id);
        categoryRepository.delete(category);
    }
}