package com.effitizer.start.repository;

import com.effitizer.start.domain.Contents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentsRepository extends JpaRepository<Contents, Long> {
}
