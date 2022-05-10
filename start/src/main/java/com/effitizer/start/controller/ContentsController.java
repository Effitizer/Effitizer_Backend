package com.effitizer.start.controller;

import com.effitizer.start.aws.S3Uploader;
import com.effitizer.start.domain.Book;
import com.effitizer.start.domain.Contentsfile;
import com.effitizer.start.domain.dto.Contents.ContentsRequest;
import com.effitizer.start.domain.dto.Contents.AllContentsRequest;
import com.effitizer.start.service.BookService;
import com.effitizer.start.service.ContentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;


@Slf4j
@Controller
public class ContentsController {
    @Autowired ContentsService contentsService;
    @Autowired BookService bookService;
    @Autowired S3Uploader s3Uploader;

    @PostMapping("api/contents/new")
    public ResponseEntity<ContentsRequest> saveContents(@RequestBody AllContentsRequest contentsRequest) {
        LinkedList<ContentsRequest> contentsRequestLinkedList = new LinkedList<>();
        contentsRequestLinkedList.addAll(contentsRequest.getContents());
        Book book = bookService.saveBook(contentsRequest.getBook_name(), contentsRequest.getWriter_name(), contentsRequest.getPublisher_name(), contentsRequest.getCategory_id());
        contentsService.saveContents()


    }

    @PostMapping("{product_id}/option/new")
    public ApiResult<String> saveOption(@PathVariable("product_id") Long productId, @RequestBody OptionRequest optionRequest){
        LinkedList<ProductChildOptionRequest> linkedList = new LinkedList<>();
        linkedList.addAll(optionRequest.getChildOptionRequests());
        productOptonService.saveOption(productId, optionRequest.getNames(), linkedList);
        return OK("저장되었습니다.");
    }

    // s3 업로드 테스트
    @PostMapping("/test/photo/{contentsid}")
    public ResponseEntity<?> uploadProfilePhoto(@PathVariable("contentsid") Long contentsId, @RequestParam("image") MultipartFile multipartFile)
            throws IOException {
        Contentsfile contentsfile = s3Uploader.upload(contentsId, multipartFile, "image");
        return ResponseEntity.ok(contentsfile);
    }


}

