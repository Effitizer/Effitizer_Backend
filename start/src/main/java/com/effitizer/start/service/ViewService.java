package com.effitizer.start.service;

import com.effitizer.start.domain.Contents;
import com.effitizer.start.domain.User;
import com.effitizer.start.domain.View;
import com.effitizer.start.repository.ViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ViewService {
    @Autowired
    ViewRepository viewRepository;
    @Autowired ContentsService contentsService;

    /**
     * view 생성

    public View plusView(Contents contents){
        // Contents에 대해 View가 존재하는지 찾기
        View view = viewRepository.findByContents(contents)
                .orElse(saveView(contents));

        // view 추가하기
        return view;
    }*/

    public View saveView(Contents contents, User user) {
        List<View> viewList = viewRepository.findByContentsAndUserOrderByCreatedDate(contents, user);
        if (viewList.size() == 0) {
            View view = View.builder()
                    .user(user)
                    .contents(contents)
                    .build();
            viewRepository.save(view);
            return view;
        }
        LocalDateTime preData = viewList.get(0).getCreatedDate();
        LocalDateTime now = LocalDateTime.now();

        if(preData.getYear() != now.getYear() || preData.getDayOfYear() != now.getDayOfYear()) {
            View view = View.builder()
                    .user(user)
                    .contents(contents)
                    .build();
            viewRepository.save(view);
            return view;
        }
        return viewList.get(0);
    }

    /*
    contents에 대햔 View 생성
     */
    public List<View> findViewByContents(Long contents_id) {
        Contents contents = contentsService.findContentsById(contents_id);
        return viewRepository.findByContents(contents);
    }
}
