package com.effitizer.start.service;

import com.effitizer.start.domain.Book;
import com.effitizer.start.domain.Publisher;
import com.effitizer.start.domain.Writer;
import com.effitizer.start.repository.BookRepository;
import com.effitizer.start.repository.CategoryRepository;
import com.effitizer.start.repository.PublisherRepository;
import com.effitizer.start.repository.WriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BookService {
    @Autowired BookRepository bookRepository;
    @Autowired WriterService writerService;
    @Autowired PublisherService publisherService;
    @Autowired CategoryRepository categoryRepository;


    public Book saveBook(String isbn, String title, String writer_name, String publisher_name, Long category_id) {
        // writer id 조회 -> 없으면 생성
        Writer writer = writerService.saveWriterOrFind(writer_name);

        // publisher id 조회 -> 없으면 생성
        Publisher publisher = publisherService.savePublisherOrFind(publisher_name);

        // 책 생성
        Book book = new Book();
        book.setWriter(writer);
        book.setPublisher(publisher);
        book.setCategory(categoryRepository.findById(String.valueOf(category_id)).get());
        book.setIsbn(isbn);
        book.setTitle(title);
        bookRepository.save(book);
        return book;
    }
}
