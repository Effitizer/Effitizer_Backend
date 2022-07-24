package com.effitizer.start.service;

import com.effitizer.start.domain.*;
import com.effitizer.start.domain.dto.Book.Request.BookRequest;
import com.effitizer.start.repository.BookRepository;
import com.effitizer.start.repository.CategoryRepository;
import com.effitizer.start.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService {
    @Autowired BookRepository bookRepository;
    @Autowired UserService userService;
    @Autowired PublisherService publisherService;
    @Autowired CategoryService categoryService;


    /**
     * Book 저장
     */
    @SneakyThrows
    public Book saveBook(BookRequest bookRequest) {
        // 동일한 isbn이 있는지 조회
        Book checkBook = bookRepository.findByIsbn(bookRequest.getIsbn())
                .orElse(null);
        if (checkBook != null) {
            throw new IllegalStateException("동일한 isbn의 책이 존재합니다.");
        }

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

    /**
     * isbn으로 Book 조회
     */
    public Book findBookByIsbn(String isbn){
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new IllegalStateException("책 정보가 올바르지 않습니다."));
    }

    /**
     * Book 전체 조회
     */
    public List<Book> findAllBook() {
        return bookRepository.findAll();
    }

    /**
     * Book id 로 조회
     */
    public Book findBookById(Long book_id) {
        return bookRepository.findById(book_id)
                .orElse(null);
    }

    /**
     * Book 수정
     */
    public Book editBook(Long book_id) {
        Book book = findBookById(book_id);
        return book;
    }

    /**
     * Book 삭제
     */
    public void deleteBook(Long book_id) {
        Book book = findBookById(book_id);
        bookRepository.delete(book);
    }
}
