package com.effitizer.start.service;

import com.effitizer.start.domain.*;
import com.effitizer.start.domain.dto.Contents.Request.ContentsRequest;
import com.effitizer.start.domain.dto.Contents.Request.OnlyContentsRequest;
import com.effitizer.start.repository.ContentsGroupsRepository;
import com.effitizer.start.repository.ContentsRepository;
import com.effitizer.start.repository.ContentsfileRepository;
import com.effitizer.start.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Autowired
    ContentsGroupsRepository contentsGroupsRepository;
    @Autowired
    GroupService groupService;

    /**
     * 콘텐츠 저장
     */
//    public Contents saveOne(Contents contents) {
//        userRepository.save(contents.getUser());
//        contentsRepository.save(contents);
//        contents.setOrder(orderService.saveOrder(contents, 1));
//        Contentsfile contentsfile = contentsfileRepository.save(new Contentsfile());
//        List<Contentsfile> contentsfileList = new ArrayList<>();
//        contentsfileList.add(contentsfile);
//        contents.setContentsfiles(contentsfileList);
//        return contents;
//    }

    /**
     *  콘텐츠 수정
     */
    public Contents update(OnlyContentsRequest onlyContentsRequest) {
        Contents contents = contentsRepository.findByIsDeletedFalseAndId(onlyContentsRequest.getId())
                .orElseThrow(()-> new IllegalStateException("삭제된 컨텐츠이거나 존재하지 않습니다"));
        User user = userService.findUserById(onlyContentsRequest.getUser_id());
        contents.setUser(user);
        Order order = contents.getOrder();
        order.setOrderNum(onlyContentsRequest.getBook_order());
        contents.setTitle(onlyContentsRequest.getTitle());
        contents.setContent(onlyContentsRequest.getContent());
        return contents;
    }

    /**
     *  콘텐츠 id로 검색
     */

    public Contents findContentsById(Long contents_id) {
        return contentsRepository.findByIsDeletedFalseAndId(contents_id)
                .orElseThrow(()->new IllegalStateException("해당 콘텐츠가 존재하지 않습니다"));
    }

    public Page<Contents> findContents(Pageable pageable){
        return contentsRepository.findByIsDeletedFalse(pageable);
    }

    /**
     *  콘텐츠 저장
    */
    public Contents saveContents(ContentsRequest contentsRequest) throws IOException {
        // book 조회
        Book book = bookService.findBookByIsbn(contentsRequest.getIsbn());
        // user 조회
        User user = userService.findUserById(contentsRequest.getUserId());
        // groups 조회
        Group group = groupService.findGroupById(contentsRequest.getGroupId());
        // Contents 생성
        Contents contents = Contents.builder()
                            .book(book)
                            .user(user)
                            .title(contentsRequest.getTitle())
                            .content(contentsRequest.getContent())
                            .build();
        contentsRepository.save(contents);

        ContentsGroups contentsGroups = ContentsGroups.builder()
                .group(group)
                .contents(contents)
                .build();
        contentsGroupsRepository.save(contentsGroups);
        // 순서 생성
        orderService.saveOrder(contents, contentsRequest.getBookOrder());
        return contents;
    }

    //컨텐츠 삭제
    public Contents deleteContents(Long contents_id){
        Contents contents= contentsRepository.findById(contents_id)
                .orElseThrow(()-> new IllegalStateException(("해당 컨텐츠를 찾을 수 없습니다")));

        contents.setDeleted(true);//삭제 표시
        return contents;
    }
}
