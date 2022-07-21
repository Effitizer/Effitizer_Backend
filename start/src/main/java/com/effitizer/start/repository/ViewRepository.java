package com.effitizer.start.repository;

import com.effitizer.start.domain.Contents;
import com.effitizer.start.domain.User;
import com.effitizer.start.domain.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ViewRepository extends JpaRepository<View, Long> {

    List<View> findByContents(Contents contents);

    List<View> findByContentsAndUserOrderByCreatedDate(Contents contents, User user);
}
