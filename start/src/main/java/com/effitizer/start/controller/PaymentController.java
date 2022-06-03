package com.effitizer.start.controller;

import com.effitizer.start.component.ReqPaymentScheduler;
import com.effitizer.start.domain.Payment;
import com.effitizer.start.domain.Subscribe;
import com.effitizer.start.domain.User;
import com.effitizer.start.domain.dto.Group.GroupDTO;
import com.effitizer.start.domain.dto.Payment.PaymentDTO;
import com.effitizer.start.domain.dto.Payment.Request.PaymentRequest;
import com.effitizer.start.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired ReqPaymentScheduler scheduler;
    @Autowired UserService userService;

    @GetMapping("/test/pay")
    public String goPay(Model model) {
        log.info("------------- payment controller -> pay");
        return "payTest";
    }

    @PostMapping("/new")
    @ResponseBody
    public ResponseEntity<?> savePayment(@RequestBody PaymentRequest paymentRequest) {
        log.info("Payment controller: /payment/new ---------------------");
        // 구독 데이터 생성
        User user = userService.findUserById(paymentRequest.getUser_id());
        Subscribe subscribe = Subscribe.builder()
                .user(user)
                .start_date(LocalDateTime.now())
                .build();

        // 결제 데이터 생성
        Payment payment = new Payment(paymentRequest, subscribe);
        PaymentDTO paymentDTO = new PaymentDTO(payment);

        // 정기 결제를 위한 스케줄러 생성
        scheduler.startScheduler(paymentRequest.getCustomer_uid(), paymentRequest.getPaid_amount());
        return ResponseEntity.ok(paymentDTO);
    }

    @PostMapping("/test/payment1")
    @ResponseBody
    public void getImportToken(@RequestBody PaymentRequest paymentRequest)
            throws JsonMappingException, JsonProcessingException {
        log.info("------------- payment controller -> payment1");
        String customer_uid = paymentRequest.getCustomer_uid();
        int price = paymentRequest.getPaid_amount();

    }
}
