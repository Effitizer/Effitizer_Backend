package com.effitizer.start.controller;

import com.effitizer.start.aws.S3Uploader;
import com.effitizer.start.domain.*;
import com.effitizer.start.domain.dto.Contents.*;
import com.effitizer.start.domain.dto.Contents.Request.AllContentsRequest;
import com.effitizer.start.domain.dto.Contents.Request.ContentsRequest;
import com.effitizer.start.domain.dto.Contents.Request.OnlyContentsRequest;
import com.effitizer.start.error.ErrorResponse;
import com.effitizer.start.service.BookService;
import com.effitizer.start.service.ContentsService;
import com.effitizer.start.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("test/api/contents")
public class ContentsController {
    @Autowired ContentsService contentsService;
    @Autowired BookService bookService;
    @Autowired UserService userService;
    @Autowired S3Uploader s3Uploader;

    /**
     * 콘텐츠 저장
     */
    @PostMapping("/new")
    public ResponseEntity<?> saveContents(@RequestBody AllContentsRequest contentsRequest) throws IOException {
        log.info("Contents controller: api/contents/new ---------------------");
        LinkedList<ContentsRequest> contentsRequestLinkedList = new LinkedList<>();
        contentsRequestLinkedList.addAll(contentsRequest.getContents());
        Book book = bookService.saveBook(contentsRequest.getIsbn(), contentsRequest.getTitle(), contentsRequest.getWriter(), contentsRequest.getPublisher(), contentsRequest.getCategory_id());
        User user = userService.findUserById(contentsRequest.getUser_id());
        List<OnlyContentsDTO> contentsDTOList = contentsService.saveContents(contentsRequestLinkedList, user, book);
        AllContentsDTO allContentsDTO = new AllContentsDTO(book, contentsDTOList);
        return ResponseEntity.ok(allContentsDTO);
    }

    /**
     * 콘텐츠 id로 콘텐츠 조회
     */
    @GetMapping("/{contents_id}")
    public ResponseEntity<?> selectContents(@PathVariable("contents_id") Long contents_id) {
        try {
            log.info("Contents controller: api/contents/{contents_id}---------------------");
            // test용 데이터
            Book book = bookService.saveOne(new Book(new Publisher("publisher"), new Writer("writer"), new Category("science"), "string", "string"));
            Contents test_contents = contentsService.saveOne(new Contents(new User("name", "email", Role.USER), book, "title", "content"));

            Contents contents = contentsService.findContensById(contents_id);
            return ResponseEntity.ok(new ContentsDTO(contents));
        }
        catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 콘텐츠 수정
     */
    @PostMapping("/{contents_id}/edit")
    public ResponseEntity<?> editOneContents(@PathVariable("contents_id") Long contents_id, @RequestBody OnlyContentsRequest onlyContentsRequest) {
        try {
            log.info("Contents controller: api/contents/{contents_id}/edit -----------------------------------");
            Contents contents = contentsService.update(onlyContentsRequest);
            return ResponseEntity.ok(new ContentsDTO(contents));
        }
        catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    // s3 업로드 테스트
    @PostMapping("/photo/{contentsid}")
    public ResponseEntity<?> uploadProfilePhoto(@PathVariable("contentsid") Long contentsId, @RequestParam("image") MultipartFile multipartFile)
            throws IOException {
        Contentsfile contentsfile = s3Uploader.upload(contentsId, multipartFile, "image");
        return ResponseEntity.ok(contentsfile);
    }

}