package com.effitizer.start.controller;

import com.effitizer.start.common.ApiResult;
import com.effitizer.start.domain.Contents;
import com.effitizer.start.domain.dto.ContentsDTO;
import com.effitizer.start.service.ContentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.effitizer.start.common.ApiResult.OK;

@Slf4j
@Controller
public class ContentController {
    @Autowired
    ContentsService contentsService;

    @PostMapping("api/contents/new")
    public ApiResult<Contents> save(@RequestBody ContentsDTO contentsdto)
    {
        return OK(contentsService.save(new Contents(contentsdto)));
    }
}
