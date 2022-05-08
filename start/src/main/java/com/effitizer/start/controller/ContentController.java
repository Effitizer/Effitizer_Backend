package com.effitizer.start.controller;

import com.effitizer.start.aws.S3Uploader;
import com.effitizer.start.common.ApiResult;
import com.effitizer.start.domain.Contents;
import com.effitizer.start.domain.Contentsfile;
import com.effitizer.start.domain.dto.ContentsDTO;
import com.effitizer.start.service.ContentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.effitizer.start.common.ApiResult.OK;

@Slf4j
@Controller
public class ContentController {
    @Autowired
    ContentsService contentsService;
    @Autowired S3Uploader s3Uploader;

    @PostMapping("api/contents/new")
    public ApiResult<Contents> save(@RequestBody ContentsDTO contentsdto)
    {
        return OK(contentsService.save(new Contents(contentsdto)));
    }

    // s3 업로드 테스트
    @PostMapping("/test/photo/{contentsid}")
    public ResponseEntity<?> uploadProfilePhoto(@PathVariable("contentsid") Long contentsId, @RequestParam("image") MultipartFile multipartFile)
            throws IOException {
        Contentsfile contentsfile = s3Uploader.upload(contentsId, multipartFile, "image");
        return ResponseEntity.ok(contentsfile);
    }


}

