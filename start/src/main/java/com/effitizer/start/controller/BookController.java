package com.effitizer.start.controller;

import com.effitizer.start.aws.S3Uploader;
import com.effitizer.start.domain.Book;
import com.effitizer.start.domain.Bookcoverfile;
import com.effitizer.start.domain.dto.Book.BookDTO;
import com.effitizer.start.domain.dto.Book.Request.BookRequest;
import com.effitizer.start.service.BookService;
import com.effitizer.start.service.CategoryService;
import com.effitizer.start.service.PublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("test/api/book")
public class BookController {
    @Autowired BookService bookService;
    @Autowired PublisherService publisherService;
    @Autowired CategoryService categoryService;
    @Autowired S3Uploader s3Uploader;

    /**
     * 책 저장
     */
    @PostMapping("/new")
    public ResponseEntity<?> saveBook(@RequestPart(required = false) BookRequest book,
                                           @RequestPart(required = false) MultipartFile book_cover)
            throws IOException {
        log.info("Book controller: api/book/new ---------------------");
        // Book 저장
        Book newBook = bookService.saveBook(book);

        // 책 커버 저장
        Bookcoverfile bookcoverfile = s3Uploader.uploadBookCoverfile(newBook.getId(), book_cover, "image");

        return ResponseEntity.ok(new BookDTO(newBook, bookcoverfile));
    }

    /**
     * 책 전체 조회
     */
    @GetMapping("")
    public ResponseEntity<?> sendBookList() {
        List<Book> bookList = bookService.findAllBook();
        return ResponseEntity.ok(bookList);

    }

    /**
     * 책 하나 조회
     */
    @PostMapping("/{book_id}")
    public ResponseEntity<?> selectBook(@PathVariable("book_id") Long book_id) {
        Book book = bookService.findBookById(book_id);
        return ResponseEntity.ok(new BookDTO(book, book.getBookcoverfile()));
    }

    /**
     * 책 정보 수정
     */
    @PutMapping("/{book_id}")
    public ResponseEntity<?> editBook(@PathVariable("book_id") Long book_id) {
        Book book = bookService.editBook(book_id);
        return ResponseEntity.ok(new BookDTO(book, book.getBookcoverfile()));
    }

    /**
     * 책 삭제
     */
    @PatchMapping("/{book_id}")
    public ResponseEntity<?> deleteBook(@PathVariable("book_id") Long book_id) {
        bookService.deleteBook(book_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
