package com.effitizer.start.controller;


import com.effitizer.start.config.auth.dto.SessionUser;

import com.effitizer.start.domain.User;
import com.effitizer.start.domain.dto.Category.CategoryDTO;
import com.effitizer.start.domain.dto.Subscribe.SubscribeDTO;
import com.effitizer.start.domain.dto.User.UserDTO;
import com.effitizer.start.error.ErrorResponse;


import com.effitizer.start.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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


    //회원 정보 조회
    @GetMapping("")
    public ResponseEntity<?> viewUserInfo() {
        try {
            log.info("User controller: /api/user ---------------------");
            SessionUser user = (SessionUser) httpSession.getAttribute("user");
            User user_info= userService.findUserByName(user.getName());
            return new ResponseEntity<>( new UserDTO(user_info), HttpStatus.OK);
        }
        catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

    }

    //회원 전체 정보 조회
    @GetMapping("/all")
    public ResponseEntity<?> viewAllUserInfo() {
        try {
            log.info("User controller: /api/user/all ---------------------");
            List<UserDTO> userDTOList = userService.findAll()
                    .stream()
                    .filter(user -> user != null)
                    .map(UserDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(userDTOList);

        }
        catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

    }
    //회원탈퇴
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(){
        try {
            log.info("User controller: /api/user/delete ---------------------");
            //
            SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
            userService.deleteUser(sessionUser.getEmail());

            return new ResponseEntity<>("successfully deleted! ",HttpStatus.OK);
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

    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.setAuthenticated(false);
        new SecurityContextLogoutHandler().logout(request,response,authentication);
        SecurityContextHolder.clearContext();
        request.logout();
        request.getSession().invalidate();
    }

}
