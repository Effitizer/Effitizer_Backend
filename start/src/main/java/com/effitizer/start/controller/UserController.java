package com.effitizer.start.controller;


import com.effitizer.start.config.auth.dto.SessionUser;

import com.effitizer.start.domain.User;
import com.effitizer.start.domain.dto.Category.CategoryDTO;
import com.effitizer.start.domain.dto.User.UserDTO;
import com.effitizer.start.error.ErrorResponse;


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

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Controller
@RequestMapping("api/user")
public class UserController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    UserService userService;





    @GetMapping("")
    public ResponseEntity<?> viewUserInfo() {
        try {
            log.info("User controller: /test/api/user ---------------------");
            SessionUser user = (SessionUser) httpSession.getAttribute("user");
            User user_info= userService.findUserByName(user.getName());
            return new ResponseEntity<>( new UserDTO(user_info), HttpStatus.OK);
        }
        catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * user 권한 관리자로 변경
     * 예외 - 이미 관리자인 경우
     */
    @PatchMapping("/edit/role/admin")
    public ResponseEntity<?> editRoleAdmin() {
        // Session
        SessionUser userSession = (SessionUser) httpSession.getAttribute("user");

        // user 권한 작가로 변경
        User user = userService.changeRole(userSession.getEmail(), "admin");
        httpSession.setAttribute("user", user);
        return ResponseEntity.ok(new UserDTO(user));

    }

    /**
     * user 권한 작가로 변경
     * 예외 - 이미 작가인 경우
     */
    @PatchMapping("/edit/role/writer")
    public ResponseEntity<?> editRoleWriter() {
        // Session
        SessionUser userSession = (SessionUser) httpSession.getAttribute("user");

        // user 권한 작가로 변경
        User user = userService.changeRole(userSession.getEmail(), "writer");
        httpSession.setAttribute("user", user);
        return ResponseEntity.ok(new UserDTO(user));
    }

}
