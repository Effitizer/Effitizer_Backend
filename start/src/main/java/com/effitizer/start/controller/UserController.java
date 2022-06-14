package com.effitizer.start.controller;


import com.effitizer.start.config.auth.dto.SessionUser;
import com.effitizer.start.domain.Contents;
import com.effitizer.start.domain.Group;
import com.effitizer.start.domain.Subscribe;
import com.effitizer.start.domain.User;
import com.effitizer.start.domain.dto.Category.CategoryDTO;
import com.effitizer.start.domain.dto.Group.Contents.GroupContentsDTO;
import com.effitizer.start.domain.dto.Group.GroupDTO;
import com.effitizer.start.domain.dto.Group.Request.GroupRequest;
import com.effitizer.start.domain.dto.SubscribeDTO;
import com.effitizer.start.error.ErrorResponse;
import com.effitizer.start.service.SubscribeService;
import com.effitizer.start.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.effitizer.start.domain.Contentsfile;
import com.effitizer.start.domain.User;
import com.effitizer.start.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Slf4j
@Controller
@RequestMapping("api/user")
public class UserController {


    @Autowired
    UserService userService;
    @Autowired
    SubscribeService subscribeService;
    @Autowired
    HttpSession httpSession;

    @GetMapping("/subscription/historys")
    public ResponseEntity<?> viewSubscriptions() {

        try {
            SessionUser user = (SessionUser) httpSession.getAttribute("user");
            Long userId= userService.findUserByName(user.getName()).getId();
            List<SubscribeDTO> subscribeDTOList = subscribeService.findSubscribesByUserId(userId)
                    .stream()
                    .filter(subscribe -> subscribe != null)
                    .map(SubscribeDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(subscribeDTOList);
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
    @GetMapping("/subscription/info")
    public ResponseEntity<?> viewSubscription() {

        try {
            SessionUser user = (SessionUser) httpSession.getAttribute("user");

            Long userId= userService.findUserByName(user.getName()).getId();
            Subscribe subscribe = subscribeService.findSubscribeByUserId(userId);
            return ResponseEntity.ok(new SubscribeDTO(subscribe));
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

    @PutMapping("/subscription/renew")
    public ResponseEntity<?> saveSubscription(){
        try {
            SessionUser user = (SessionUser) httpSession.getAttribute("user");
            User user_info= userService.findUserByName(user.getName());
            Subscribe subscribe = subscribeService.findSubscribeByUserId(user_info.getId());

            LocalDateTime current = LocalDateTime.now();
            if(subscribe.getExpired_date().isAfter(current)) //구독 진행중이지만 결제일을 연장하고 싶은 경우
            {
                subscribeService.updateOne(user_info,subscribe.getExpired_date());
            }
            else{//구독이 만료되었지만 다시시작하거나 새로운 구독을 하는 경우
                subscribeService.saveOne(user_info);
            }

            return ResponseEntity.ok("Subscription Success ");
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
