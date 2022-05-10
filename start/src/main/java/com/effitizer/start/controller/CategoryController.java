package com.effitizer.start.controller;

import com.effitizer.start.domain.Contentsfile;
import com.effitizer.start.domain.dto.Category.CategoryDTO;
import com.effitizer.start.repository.CategoryRepository;
import com.effitizer.start.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class CategoryController {
    @Autowired CategoryService categoryService;

    /**
     * 카테고리 리스트 조회
     */
    @GetMapping("/api/category")
    public ResponseEntity<List<?>> sendCategoryList() {
        try {
            log.info("Category controller: /api/category ---------------------");
            // 테스트용 생성 데이터
            categoryService.saveCategory("경제");
            categoryService.saveCategory("영어");
            categoryService.saveCategory("고전");
            categoryService.saveCategory("IT");

            List<CategoryDTO> categoryDTOList = categoryService.findCategorys()
                    .stream()
                    .filter(category -> category != null)
                    .map(CategoryDTO::new)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(categoryDTOList, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }
}
