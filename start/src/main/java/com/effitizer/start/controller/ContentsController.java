package com.effitizer.start.controller;

import com.effitizer.start.aws.S3Uploader;
import com.effitizer.start.config.auth.dto.SessionUser;
import com.effitizer.start.domain.*;
import com.effitizer.start.domain.dto.Contents.*;
import com.effitizer.start.domain.dto.Contents.Contentsfile.ContentsContentsfileDTO;
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

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("api/contents")
public class ContentsController {
    @Autowired ContentsService contentsService;
    @Autowired BookService bookService;
    @Autowired UserService userService;
    @Autowired S3Uploader s3Uploader;
    @Autowired HttpSession httpSession;
    /**
     * 콘텐츠 저장
     * ADMIN만 가능
     */
    @PostMapping("/new")
    public ResponseEntity<?> saveContents(@RequestPart(required = false) ContentsRequest contents,
                                          @RequestPart(required = false) List<MultipartFile> contents_images)
            throws IOException {
        log.info("Contents controller: api/contents/new ---------------------");
        // Session
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        Long userId= userService.findUserByName(sessionUser.getName()).getId();

        // Contents 저장
        Contents newContent = contentsService.saveContents(contents);

        // Contents file 저장
        List<ContentsContentsfileDTO> contentsContentsfileDTOS = new ArrayList<>();
        for (MultipartFile multipartFile: contents_images) {
            Contentsfile contentsfile = s3Uploader.upload(newContent, multipartFile, "image");
            contentsContentsfileDTOS.add(new ContentsContentsfileDTO(contentsfile.getId(), contentsfile.getPath()));
        }

        return ResponseEntity.ok(new ContentsDTO(newContent, contentsContentsfileDTOS));
    }
//    @PostMapping("/new")
//    public ResponseEntity<?> saveContents(@RequestPart(required = false) ContentsRequest contents,
//                                           @RequestPart(required = false) List<MultipartFile> contents_images)
//            throws IOException {
//        log.info("Contents controller: api/contents/new ---------------------");
//
//        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
//        Long userId= userService.findUserByName(sessionUser.getName()).getId();
//        if(sessionUser.getRole().equals(Role.GUEST))
//            return ResponseEntity.ok("Guest cannot create new contents! ");
//
//
//    }


    /**
     * 콘텐츠 id로 콘텐츠 조회
     */
    @GetMapping("/{contents_id}")
    public ResponseEntity<?> selectContents(@PathVariable("contents_id") Long contents_id) {
        try {
            log.info("Contents controller: api/contents/{contents_id}---------------------");
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
     * GUEST 제외 가능
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


}