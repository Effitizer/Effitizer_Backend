package com.effitizer.start.controller;

import com.effitizer.start.config.auth.dto.SessionUser;
import com.effitizer.start.domain.Category;
import com.effitizer.start.domain.Role;
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

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("api/category")
public class CategoryController {
    @Autowired CategoryService categoryService;
    @Autowired HttpSession httpSession;
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
     * ADMIN만 가능
     */
    @PostMapping("/new")
    public ResponseEntity<?> saveCategory(@RequestBody CategoryRequest categoryRequest) {
        try {
            log.info("Category controller: /api/category/new ---------------------");
            Category category = new Category();
            SessionUser user = (SessionUser) httpSession.getAttribute("user");

            if(!user.getRole().equals(Role.ADMIN))
                return new ResponseEntity<>("Only admin can create category", HttpStatus.BAD_REQUEST);
            else{
                category = categoryService.saveCategory(categoryRequest.getName());
            }

            return new ResponseEntity<>(new CategoryDTO(category), HttpStatus.OK);
        }
        catch (IllegalStateException e) {
            // 동일한 이름이 이미 존재할 경우
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }
        catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 카테고리 조회
     */
    @GetMapping("/{category_id}")
    public ResponseEntity<?> showCategory(@PathVariable("category_id") Long category_id) {
        Category category = categoryService.findCategoryById(category_id);
        return new ResponseEntity<>(new CategoryDTO(category), HttpStatus.OK);
    }

    /**
     * 카테고리 수정
     */
    @PutMapping("/{category_id}/edit")
    public ResponseEntity<?> editCategory(@PathVariable("category_id") Long category_id,
                                          @RequestBody CategoryRequest categoryRequest) {
        Category category = categoryService.editCategory(category_id, categoryRequest.getName());
        return new ResponseEntity<>(new CategoryDTO(category), HttpStatus.OK);
    }

    /**
     * 카테고리 삭제
     */
    @PatchMapping("/{category_id}/delete")
    public ResponseEntity<?> deleteCategory(@PathVariable("category_id") Long category_id) {
        categoryService.deleteCategory(category_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
