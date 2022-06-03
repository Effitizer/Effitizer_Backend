package com.effitizer.start.controller;

import com.effitizer.start.config.auth.dto.SessionUser;
import com.effitizer.start.domain.Contents;
import com.effitizer.start.domain.Group;
import com.effitizer.start.domain.dto.Group.Contents.GroupContentsDTO;
import com.effitizer.start.domain.dto.Group.GroupDTO;
import com.effitizer.start.domain.dto.Group.Request.GroupRequest;
import com.effitizer.start.error.ErrorResponse;
import com.effitizer.start.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("test/api/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    HttpSession httpSession;

    @PostMapping("/subscription/info")
    public ResponseEntity<?> viewSubscription() {

        try {
            SessionUser user = (SessionUser) httpSession.getAttribute("user");
            System.out.println(user.getName());
            return ResponseEntity.ok(user.getName());
        }
        catch (IllegalStateException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }
        catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

    }

}
