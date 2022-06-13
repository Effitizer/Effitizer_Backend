package com.effitizer.start.repository;

import com.effitizer.start.domain.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    List<Subscribe> findByUserId(Long userId);

    Optional<Subscribe> findFirstByUserIdOrderByIdDesc(Long userId);
}
