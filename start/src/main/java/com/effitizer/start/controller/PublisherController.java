package com.effitizer.start.controller;

import com.effitizer.start.service.PublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("api/publisher")
public class PublisherController {
    @Autowired PublisherService publisherService;


}
