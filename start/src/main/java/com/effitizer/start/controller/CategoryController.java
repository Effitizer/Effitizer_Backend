package com.effitizer.start.controller;

import com.effitizer.start.domain.Category;
import com.effitizer.start.domain.dto.Category.CategoryDTO;
import com.effitizer.start.domain.dto.Category.Request.CategoryRequest;
import com.effitizer.start.error.ErrorResponse;
import com.effitizer.start.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("test/api/category")
public class CategoryController {
    @Autowired CategoryService categoryService;

    /**
     * 카테고리 리스트 조회
     */
    @GetMapping("")
    public ResponseEntity<?> sendCategoryList() {
        try {
            log.info("Category controller: /api/category ---------------------");

            List<CategoryDTO> categoryDTOList = categoryService.findCategorys()
                    .stream()
                    .filter(category -> category != null)
                    .map(CategoryDTO::new)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(categoryDTOList, HttpStatus.OK);
        }
        catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 카테고리 저장
     */
    @PostMapping("/new")
    public ResponseEntity<?> saveCategory(@RequestBody CategoryRequest categoryRequest) {
        try {
            log.info("Category controller: /api/category/new ---------------------");
            Category category = categoryService.saveCategory(categoryRequest.getName());
            return new ResponseEntity<>(new CategoryDTO(category), HttpStatus.OK);
        }
        catch (IllegalStateException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }
        catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
