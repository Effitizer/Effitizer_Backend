package com.effitizer.start.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@RequestMapping("api/user")
public class UserController {
    @Autowired UserService userService;
    @Autowired SubscribeService subscribeService;
    @Autowired HttpSession httpSession;

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
