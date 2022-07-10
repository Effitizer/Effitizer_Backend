package com.effitizer.start.service;

import com.effitizer.start.domain.Contents;
import com.effitizer.start.domain.View;
import com.effitizer.start.repository.ViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ViewService {
    @Autowired
    ViewRepository viewRepository;

    /**
     * view 생성
     */
    public View saveView(Contents contents){
        View view = View.builder()
                .contents(contents)
                .build();
        viewRepository.save(view);
        return view;
    }
}
