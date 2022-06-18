package com.effitizer.start.repository;

import com.effitizer.start.domain.Bookcoverfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookcoverfileRepository extends JpaRepository<Bookcoverfile, Long> {

}
