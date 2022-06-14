package com.effitizer.start.service;

import com.effitizer.start.domain.*;
import com.effitizer.start.domain.dto.Contents.Request.ContentsRequest;
import com.effitizer.start.domain.dto.Contents.Request.OnlyContentsRequest;
import com.effitizer.start.repository.ContentsRepository;
import com.effitizer.start.repository.ContentsfileRepository;
import com.effitizer.start.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ContentsService {
    @Autowired ContentsRepository contentsRepository;
    @Autowired UserService userService;
    @Autowired UserRepository userRepository;
    @Autowired OrderService orderService;
    @Autowired ContentsfileRepository contentsfileRepository;
    @Autowired BookService bookService;

    /**
     * 콘텐츠 저장
     */
    public Contents saveOne(Contents contents) {
        userRepository.save(contents.getUser());
        contentsRepository.save(contents);
        contents.setOrder(orderService.saveOrder(contents, 1));
        Contentsfile contentsfile = contentsfileRepository.save(new Contentsfile());
        List<Contentsfile> contentsfileList = new ArrayList<>();
        contentsfileList.add(contentsfile);
        contents.setContentsfiles(contentsfileList);
        return contents;
    }

    /**
     *  콘텐츠 수정
     */
    public Contents update(OnlyContentsRequest onlyContentsRequest) {
        Contents contents = contentsRepository.getById(onlyContentsRequest.getId());
        User user = userService.findUserById(onlyContentsRequest.getUser_id());
        contents.setUser(user);
        Order order = contents.getOrder();
        order.setOrder_num(onlyContentsRequest.getBook_order());
        contents.setTitle(onlyContentsRequest.getTitle());
        contents.setContent(onlyContentsRequest.getContent());
        return contents;
    }

    /**
     *  콘텐츠 id로 검색
     */
    public Contents findContensById(Long contents_id) {
        return contentsRepository.findById(contents_id)
                .orElse(null);
    }

    /**
     *  콘텐츠 저장
    */
    public Contents saveContents(ContentsRequest contentsRequest) throws IOException {
        // book 조회
        Book book = bookService.findBookByIsbn(contentsRequest.getIsbn());
        // user 조회
        User user = userService.findUserById(contentsRequest.getUser_id());
        // Contents 생성
        Contents contents = Contents.builder()
                            .book(book)
                            .user(user)
                            .title(contentsRequest.getTitle())
                            .content(contentsRequest.getContent())
                            .build();
        contentsRepository.save(contents);
        // 순서 생성
        orderService.saveOrder(contents, contentsRequest.getBook_order());
        return contents;
    }
}
