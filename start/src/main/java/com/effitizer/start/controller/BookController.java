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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
}
