package com.effitizer.start.controller;

import com.effitizer.start.aws.S3Uploader;
import com.effitizer.start.domain.Book;
import com.effitizer.start.domain.Contents;
import com.effitizer.start.domain.Contentsfile;
import com.effitizer.start.domain.User;
import com.effitizer.start.domain.dto.Contents.AllContentsDTO;
import com.effitizer.start.domain.dto.Contents.ContentsDTO;
import com.effitizer.start.domain.dto.Contents.ContentsRequest;
import com.effitizer.start.domain.dto.Contents.AllContentsRequest;
import com.effitizer.start.service.BookService;
import com.effitizer.start.service.ContentsService;
import com.effitizer.start.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


@Slf4j
@Controller
public class ContentsController {
    @Autowired ContentsService contentsService;
    @Autowired BookService bookService;
    @Autowired UserService userService;
    @Autowired S3Uploader s3Uploader;

    @PostMapping("api/contents/new")
    public ResponseEntity<?> saveContents(@RequestBody AllContentsRequest contentsRequest) {
        LinkedList<ContentsRequest> contentsRequestLinkedList = new LinkedList<>();
        contentsRequestLinkedList.addAll(contentsRequest.getContents());
        Book book = bookService.saveBook(contentsRequest.getIsbn(), contentsRequest.getTitle(), contentsRequest.getWriter(), contentsRequest.getPublisher(), contentsRequest.getCategory_id());
        User user = userService.findUserById(contentsRequest.getUser_id())
        List<ContentsDTO> contentsDTOList = contentsService.saveContents(contentsRequestLinkedList, user, book);
        AllContentsDTO allContentsDTO = new AllContentsDTO(book.getId(), book.getTitle(), book.getIsbn(), book.getWriter().getName(), book.getPublisher().getName(), contentsDTOList);
        return ResponseEntity.ok(allContentsDTO);

    }

    // s3 업로드 테스트
    @PostMapping("/test/photo/{contentsid}")
    public ResponseEntity<?> uploadProfilePhoto(@PathVariable("contentsid") Long contentsId, @RequestParam("image") MultipartFile multipartFile)
            throws IOException {
        Contentsfile contentsfile = s3Uploader.upload(contentsId, multipartFile, "image");
        return ResponseEntity.ok(contentsfile);
    }


}

