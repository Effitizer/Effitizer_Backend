package com.effitizer.start.repository;

import com.effitizer.start.domain.Contents;
import com.effitizer.start.domain.vo.ContentsDataVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentsRepository extends JpaRepository<Contents, Long> {

    @Query("select c.book_id, sum(c.view) as sum_view from Contents c group by c.book3 ")
    List<ContentsDataVO> findSumContentsViews();
}
