package com.effitizer.start.repository;

import com.effitizer.start.domain.Publisher;
import com.effitizer.start.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, String> {
    Optional<Publisher> findByName(String name);

}
