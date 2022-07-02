package com.effitizer.start.controller;

import com.effitizer.start.component.ReqPaymentScheduler;
import com.effitizer.start.config.auth.dto.SessionUser;
import com.effitizer.start.domain.*;
import com.effitizer.start.domain.dto.Contents.ContentsDTO;
import com.effitizer.start.domain.dto.Subscribe.SubscribeDTO;
import com.effitizer.start.error.ErrorResponse;
import com.effitizer.start.service.SubscribeService;
import com.effitizer.start.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/api/subscribe")
public class SubscribeController {
    @Autowired
    SubscribeService subscribeService;
    @Autowired
    ReqPaymentScheduler scheduler;
    @Autowired
    UserService userService;
    @Autowired
    HttpSession httpSession;

    @GetMapping("/stop/{subscribe_id}")
    public ResponseEntity<?> expireSubscribe(@PathVariable("subscribe_id") Long subscribe_id) {

        try{
            log.info("Subscribe controller: api/subscribe/stop/{subscribe_id}---------------------");
            SessionUser user = (SessionUser) httpSession.getAttribute("user");
            User user_info= userService.findUserByName(user.getName());
            Role user_role= user_info.getRole();
            if(user_role.equals(Role.ADMIN) || user_role.equals(Role.USER)){//user랑 admin만 삭제가능
                scheduler.stopScheduler();
                Subscribe subscribe = subscribeService.updateExpire(subscribe_id);
                return ResponseEntity.ok(new SubscribeDTO(subscribe));
            }
            return new ResponseEntity<>("Only admin & user can stop their subscription", HttpStatus.BAD_REQUEST);
        }catch (IllegalStateException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }
        catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

    }


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
