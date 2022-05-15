package com.effitizer.start.repository;

import com.effitizer.start.domain.Book;
import com.effitizer.start.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByName(String name);

    Optional<Book> findByIsbn(String isbn);
}
