package com.effitizer.start.service;

import com.effitizer.start.domain.Book;
import com.effitizer.start.domain.Bookcoverfile;
import com.effitizer.start.repository.BookcoverfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Service
@Transactional
public class BookcoverfileService {
    @Autowired
    BookcoverfileRepository bookcoverfileRepository;

    /**
     * bookcoverfile 저장
     */
    public Bookcoverfile saveBookCoverfile(Book book, String fileName, String uploadImageUrl, Long size, String extend, String origin_filename) {
        Bookcoverfile bookcoverfile = Bookcoverfile.builder()
                .book(book)
                .extend(extend)
                .name(fileName)
                .size(size)
                .path(uploadImageUrl)
                .real_name(origin_filename)
                .build();
        bookcoverfileRepository.save(bookcoverfile);
        return bookcoverfile;
    }
}
