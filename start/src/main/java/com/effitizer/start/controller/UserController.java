package com.effitizer.start.controller;


import com.effitizer.start.config.auth.dto.SessionUser;
import com.effitizer.start.domain.Book;
import com.effitizer.start.domain.Bookcoverfile;
import com.effitizer.start.domain.User;
import com.effitizer.start.domain.dto.Book.BookDTO;
import com.effitizer.start.domain.dto.Book.Request.BookRequest;
import com.effitizer.start.domain.dto.User.UserDTO;
import com.effitizer.start.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;


@Slf4j
@Controller
@RequestMapping("api/user")
public class UserController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    UserService userService;


    /**
     * user 권한 작가로 변경
     * 예외 - 이미 작가인 경우
     */
    @GetMapping("/edit/role/writer")
    public ResponseEntity<?> editRoleWriter() {
        // Session
        SessionUser userSession = (SessionUser) httpSession.getAttribute("user");

        // user 권한 작가로 변경
        User user = userService.changeRole(userSession.getEmail(), "writer");
        httpSession.setAttribute("user", user);
        return ResponseEntity.ok(new UserDTO(user));
    }

    /**
     * user 권한 관리자로 변경
     * 예외 - 이미 관리자인 경우
     */
    @GetMapping("/edit/role/admin")
    public ResponseEntity<?> editRoleAdmin() {
        // Session
        SessionUser userSession = (SessionUser) httpSession.getAttribute("user");

        // user 권한 작가로 변경
        User user = userService.changeRole(userSession.getEmail(), "admin");
        httpSession.setAttribute("user", user);
        return ResponseEntity.ok(new UserDTO(user));
    }

}
