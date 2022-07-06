package com.effitizer.start.repository;

import com.effitizer.start.domain.Contents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContentsRepository extends JpaRepository<Contents, Long> {
    Optional<Contents> findByIsDeletedFalseAndId(Long contents_id);
    Page<Contents> findByIsDeletedFalse(Pageable pageable);
}
