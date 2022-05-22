package com.effitizer.start.repository;

import com.effitizer.start.domain.Publisher;
import com.effitizer.start.domain.Writer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WriterRepository extends JpaRepository<Writer, String> {
    Optional<Writer> findByName(String name);
}
