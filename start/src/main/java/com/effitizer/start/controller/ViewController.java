package com.effitizer.start.controller;

import com.effitizer.start.config.auth.dto.SessionUser;
import com.effitizer.start.domain.User;
import com.effitizer.start.domain.dto.User.UserDTO;
import com.effitizer.start.error.ErrorResponse;
import com.effitizer.start.service.UserService;
import com.effitizer.start.service.ViewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("api/view")
public class ViewController {
    @Autowired
    ViewService viewService;
    @Autowired
    UserService userService;
    @Autowired
    HttpSession httpSession;

}
