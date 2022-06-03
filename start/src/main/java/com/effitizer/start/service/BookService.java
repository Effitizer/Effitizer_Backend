package com.effitizer.start.service;

import com.effitizer.start.domain.*;
import com.effitizer.start.domain.dto.Book.Request.BookRequest;
import com.effitizer.start.repository.BookRepository;
import com.effitizer.start.repository.CategoryRepository;
import com.effitizer.start.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookService {
    @Autowired BookRepository bookRepository;
    @Autowired UserService userService;
    @Autowired PublisherService publisherService;
    @Autowired CategoryService categoryService;


    public Book saveBook(BookRequest bookRequest) {
        // writer id 조회
        User writer = userService.findUserById(bookRequest.getWriter_id());
        if (writer.getRole() != Role.WRITER) {
            throw new IllegalStateException("작가 정보가 올바르지 않습니다.");
        }

        // publisher 조회
        Publisher publisher = publisherService.findPublisherById(bookRequest.getPublisher_id());

        // category 조회
        Category category = categoryService.findCategoryById(bookRequest.getCategory_id());

        // 책 생성
        Book book = Book.builder()
                    .user(writer)
                    .publisher(publisher)
                    .category(category)
                    .isbn(bookRequest.getIsbn())
                    .title(bookRequest.getTitle())
                    .build();
        bookRepository.save(book);
        return book;
    }
}
